from index_build import *
from search_queries import *

buildIndex()
generateMetaIndex('index.txt')

print(indexEntryFor('very'))
#A = ItemStream(indexEntryFor('pursued'))
#B = ItemStream(indexEntryFor('exit'))
#print(A.peek(), B.peek())
#H = HitStream([A, B], 1, 2)
#while h := H.next():
#    print(h)

#print('Done with H')
easySearch(['pursued', 'exit'])
easySearch(['palpable', 'very'])

advancedSearch(['friends', 'romans', 'countrymen'], 5, 2, 20)