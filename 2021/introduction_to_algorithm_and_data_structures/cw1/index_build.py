# Inf2-IADS Coursework 1, October 2020
# Python source file: index_build.py
# Author: John Longley

# TEMPLATE FILE
# Please add your code at the point marked: # TODO

# PART A: INDEXING A LARGE SET OF PLAINTEXT FILES

import linecache
import os
import math
import buffered_io
from buffered_io import *

# Global variables

CorpusFiles = {
    'CAA': 'Carroll_Alice_in_Wonderland.txt',
    'DCC': 'Dickens_Christmas_Carol.txt',
    'SJH': 'Stevenson_Jekyll_and_Hyde.txt',
    'SCW': 'Shakespeare_Complete_Works.txt',
    'TWP': 'Tolstoy_War_and_Peace.txt',
}
# each file should be identified by a three-letter code
# CAA, DCC, SJH are small, SCW and TCP are larger

IndexFile = 'index.txt'
# name of main index file to be generated

MetaIndex = {'': 0}
# dictionary to be populated
# MetaIndex[k] will give line number in IndexFile for key k

MetaIndexOp = (lambda s: 0)

# Initial scan to determine number of lines in a given text file


def getNumberOfLines(filename):
    reader = BufferedInput(filename, 0.8)
    lines = 0
    chunk = reader.readchunk()
    while chunk != []:
        lines += len(chunk)
        chunk = reader.readchunk()
    reader.close()
    return lines


# Extracting list of words present in a single text line:


def getWords(s):
    t = s  # could do some translation here to process accented symbols etc.
    words, flg = [], False
    for i in range(len(t)):
        if not flg:
            if t[i].isalpha():
                # potential start of word
                flg = True
                j = i
        else:
            if not t[i].isalpha():
                # potential end of word
                flg = False
                # design decision: we ignore words of length < 4
                if i - j >= 4:
                    words.append(t[j:i].casefold())
        # assumes some terminator like \n is present
    return words


# Generation of unsorted index entries for a given textfile


def generateIndexEntries(filename, filecode, writer):
    numberOfLines = getNumberOfLines(filename)
    digits = int(math.log10(numberOfLines)) + 1
    padCtrl = '0' + str(digits)  # controls leading zero padding
    reader = BufferedInput(filename, 0.2)
    currline = reader.readln()
    inlineNo = 1
    outlineNo = 0
    while currline != None:
        # process currline:
        words = getWords(currline)
        for w in words:
            writer.writeln(w + ':' + filecode + format(inlineNo, padCtrl) +
                           '\n')
        outlineNo += len(words)
        # next line:
        inlineNo += 1
        currline = reader.readln()
    reader.close()
    return outlineNo  # for testing


def generateAllIndexEntries(entryfile):
    global CorpusFiles
    writer = BufferedOutput(entryfile, 0.7)
    outlines = 0
    for filecode in CorpusFiles:
        outlines += generateIndexEntries(CorpusFiles[filecode], filecode,
                                         writer)
    writer.flush()
    return outlines


# Provide the following:


def splitIntoSortedChunks(entryfile):
    reader = BufferedInput(entryfile, 0.3)
    blockNo = 0
    chunk = reader.readchunk()
    while chunk != []:
        chunk.sort()
        blockfile = open('temp_' + str(blockNo) + '_' + str(blockNo + 1),
                         'w',
                         encoding='utf-8')
        # output file written all at once, so no need for buffering
        blockfile.writelines(chunk)
        blockfile.close()
        blockNo += 1
        chunk = reader.readchunk()
    reader.close()
    return blockNo


# TODO
# Add your code here.


def mergeFiles(a, b, c):
    # Preparation
    f_ab = f'temp_{a}_{b}'
    f_bc = f'temp_{b}_{c}'
    f_ac = f'temp_{a}_{c}'
    reader_ab = BufferedInput(f_ab, 0.3)
    reader_bc = BufferedInput(f_bc, 0.3)
    writer_ac = BufferedOutput(f_ac, 0.3)
    # Initialise both elements
    ab = reader_ab.readln()
    bc = reader_bc.readln()
    # Merging while neither file is empty
    while ab and bc:
        if ab < bc:
            writer_ac.writeln(ab)
            ab = reader_ab.readln()
        else:
            writer_ac.writeln(bc)
            bc = reader_bc.readln()
    # While the other stream is not empty, dump the contents
    # into the output file.
    # One of the files is empty for sure, so only one of
    # these loops will actually run.
    while ab:
        writer_ac.writeln(ab)
        ab = reader_ab.readln()
    while bc:
        writer_ac.writeln(bc)
        bc = reader_bc.readln()
    # Final clean-up
    reader_ab.close()
    reader_bc.close()
    writer_ac.flush()
    os.remove(f_ab)
    os.remove(f_bc)
    # returning filename to be consistent with mergeFilesInRange
    return f_ac


def mergeFilesInRange(a, c):  # T(n) = 2T(n/2) + Theta(n)
    # base case, assuming a < c always holds
    if a + 1 == c:
        return f'temp_{a}_{c}'
    # recursive calls with most efficient setup,
    # i.e. splitting the interval in the middle
    b = (a + c) // 2
    mergeFilesInRange(a, b)  # T(n/2)
    mergeFilesInRange(b, c)  # T(n/2)
    # final merge
    return mergeFiles(a, b, c)  # Theta(n)


# Putting it all together:


def sortRawEntries(entryfile):
    chunks = splitIntoSortedChunks(entryfile)
    outfile = mergeFilesInRange(0, chunks)
    return outfile


# Now compile the index file itself, by 'compressing' the entries for each key
# into a single line.


def createIndexFromEntries(entryfile, indexfile):
    reader = BufferedInput(entryfile, 0.4)
    writer = BufferedOutput(indexfile, 0.4)
    inl = reader.readln()
    currKey, currDoc, lineBuffer = '', '', ''
    while inl != None:
        # get keyword and ref, start ref list:
        colon = inl.index(':')
        key = inl[:colon]
        doc = inl[colon + 1:colon + 4]  # three-letter doc identifiers
        j = colon + 4
        while inl[j] == '0':
            j += 1
        line = inl[j:-1]
        if key != currKey:
            # new key: start a new line in index
            if lineBuffer != '':
                writer.writeln(lineBuffer + '\n')
            currKey = key
            currDoc = ''
            lineBuffer = key + ':'
        if currDoc == '':
            # first doc for this key entry
            currDoc = doc
            lineBuffer = lineBuffer + doc + line
        elif doc != currDoc:
            # new doc within this key entry
            currDoc = doc
            lineBuffer = lineBuffer + ',' + doc + line
        else:
            lineBuffer = lineBuffer + ',' + line
        inl = reader.readln()
    # write last line and clean up
    writer.writeln(lineBuffer + '\n')
    writer.flush()
    reader.close()


# Generating the meta-index to the index as a Python dictionary.


def generateMetaIndex(indexFile):
    global MetaIndex, MetaIndexOp
    MetaIndex.clear()
    reader = BufferedInput(indexFile, 0.9)
    indexline = 1
    inl = reader.readln()
    while inl != None:
        key = inl[:inl.index(':')]
        MetaIndex[key] = indexline
        indexline += 1
        inl = reader.readln()
    reader.close()
    MetaIndexOp = (lambda s: MetaIndex[s])


def buildIndex():
    rawEntryFile = 'raw_entries'
    entries = generateAllIndexEntries(rawEntryFile)
    sortedEntryFile = sortRawEntries(rawEntryFile)
    global IndexFile
    createIndexFromEntries(sortedEntryFile, IndexFile)
    generateMetaIndex(IndexFile)
    os.remove(rawEntryFile)
    os.remove(sortedEntryFile)
    print('Success! ' + str(len(MetaIndex)) + ' keys, ' + str(entries) +
          ' entries.')


# Accessing the index using 'linecache' (random access to text files by line)


def indexEntryFor(key):
    global IndexFile, MetaIndex, MetaIndexOp
    try:
        lineNo = MetaIndexOp(key)  # allows for other meta-indexing schemes
        indexLine = linecache.getline(IndexFile, lineNo)
    except KeyError:
        return None
    colon = indexLine.index(':')
    if indexLine[:colon] == key:
        return indexLine[colon + 1:]
    else:
        raise Exception('Wrong key in index line.')


# End of file
