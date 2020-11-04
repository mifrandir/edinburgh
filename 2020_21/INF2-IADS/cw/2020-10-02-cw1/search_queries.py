# Inf2-IADS Coursework 1, October 2020
# Python source file: search_queries.py
# Author: John Longley

# TEMPLATE FILE
# Please add your code at the point marked: # TODO

# PART B: PROCESSING SEARCH QUERIES

import linecache
import index_build

# We find hits for queries using the index entries for the search terms.
# Since index entries for common words may be large, we don't want to
# process the entire index entry before commencing a search.
# Instead, we process the index entry as a stream of items, each of which
# references an occurrence of the search term.

# For example, the (short) index entry

#    'ABC01,23,DEF004,056,789\n'

# generates a stream which successively yields the items

#    ('ABC',1), ('ABC',23), ('DEF',4), ('DEF',56), ('DEF',789), None, None, ...

# Item streams also support peeking at the next item without advancing.


class ItemStream:
    def __init__(self, entryString):
        self.entryString = entryString
        self.pos = 0
        self.doc = 0
        self.comma = 0

    def updateDoc(self):
        if self.entryString[self.pos].isalpha():
            self.doc = self.entryString[self.pos:self.pos + 3]
            self.pos += 3

    def peek(self):
        if self.pos < len(self.entryString):
            self.updateDoc()
            self.comma = self.entryString.find(',', self.pos)
            # yields -1 if no more commas after pos
            line = int(self.entryString[self.pos:self.comma])
            # magically works even when comma == -1, thanks to \n
            return (self.doc, line)
        # else will return None

    def pop(self):
        e = self.peek()
        if self.comma == -1:
            self.pos = len(self.entryString)
        else:
            self.pos = self.comma + 1
        return e


# TODO
# Add your code here.

class HitStream:

    def __init__(self, itemStreams, lineWindow, minRequired):
        self.itemStreams = itemStreams
        self.lineWindow = lineWindow
        self.minRequired = minRequired
        self.last = 0
        self.empty = False
        self.min_s = None
        self.num_empty = 0

    def update_min_s(self):
        self.min_s = self.itemStreams[0]
        for s in self.itemStreams:
            v = s.peek()
            if not v:
                continue
            min_v = self.min_s.peek()
            if not min_v or min_v > v:
                self.min_s = s

    def next_document(self):
        doc = self.min_s.pop()
        for s in self.itemStreams:
            while (v := s.peek()) and v[0] == doc[0]:
                s.pop()

    def check_documents(self):
        self.update_min_s()
        min_v = self.min_s.peek()
        if not min_v:
            self.last = None
            return True
        num_right_document = 0
        for s in self.itemStreams:
            if s.peek() and s.peek()[0] == min_v[0]:
                num_right_document += 1
        if num_right_document < self.minRequired:
            self.next_document()
            return False
        return True

    def tentative_next(self):
        # while we don't have enough streams with the same current
        # docuemnt, keep updating
        while not self.check_documents():
            pass
        # filter out empty streams
        self.itemStreams = [s for s in self.itemStreams if s.peek()]

    def next(self):
        if self.last == None:
            return None
        while True:
            if self.min_s:
                # since the line number in the output is only dependent
                # on the minimum line number, this is the only one we
                # need to increase
                self.min_s.pop()
            self.tentative_next()
            if len(self.itemStreams) < self.minRequired:
                self.last = None
                return None
            min_v = self.min_s.peek()
            num_matches = 0
            for s in self.itemStreams:
                v = s.peek()
                if v[0] == min_v[0] and min_v[1] + self.lineWindow > v[1]:
                    num_matches += 1
            if num_matches >= self.minRequired:
                return min_v
        self.last = None
        return self.last


# Displaying hits as corpus quotations:


def displayLines(startref, lineWindow):
    # global CorpusFiles
    if startref is not None:
        doc = startref[0]
        docfile = index_build.CorpusFiles[doc]
        line = startref[1]
        print((doc + ' ' + str(line)).ljust(16) +
              linecache.getline(docfile, line).strip())
        for i in range(1, lineWindow):
            print(' ' * 16 + linecache.getline(docfile, line + i).strip())
        print('')


def displayHits(hitStream, numberOfHits, lineWindow):
    for i in range(0, numberOfHits):
        startref = hitStream.next()
        if startref is None:
            print('-' * 16)
            break
        displayLines(startref, lineWindow)
    linecache.clearcache()
    return hitStream


# Putting it all together:

currHitStream = None

currLineWindow = 0


def advancedSearch(keys, lineWindow, minRequired, numberOfHits=5):
    indexEntries = [index_build.indexEntryFor(k) for k in keys]
    if not all(indexEntries):
        message = "Words absent from index:  "
        for i in range(0, len(keys)):
            if indexEntries[i] is None:
                message += (keys[i] + " ")
        print(message + '\n')
    itemStreams = [ItemStream(e) for e in indexEntries if e is not None]
    if len(itemStreams) >= minRequired:
        global currHitStream, currLineWindow
        currHitStream = HitStream(itemStreams, lineWindow, minRequired)
        currLineWindow = lineWindow
        displayHits(currHitStream, numberOfHits, lineWindow)


def easySearch(keys, numberOfHits=5):
    global currHitStream, currLineWindow
    advancedSearch(keys, 1, len(keys), numberOfHits)


def more(numberOfHits=5):
    global currHitStream, currLineWindow
    displayHits(currHitStream, numberOfHits, currLineWindow)


# End of file
