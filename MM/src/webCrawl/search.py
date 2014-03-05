'''

@author: User
'''

class Search(object):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''

    def lookup(self, index, keyword,ranks):

        if keyword in index:
            bestFit = index[keyword][0]
            for each in index[keyword] :
                if ranks[each] > ranks[bestFit] :
                    bestFit = each
            return bestFit
        else:
            return None
# search the for the keyword in the index
# if there return the highest ranking url
# if not return none

