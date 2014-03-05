'''

@author: User
'''

class Database(object):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''

    def compute_ranks(self, graph):
        d = 0.8  # damping factor
        numloops = 10

        ranks = {}            # key - the url; value - how important the url is
        npages = len(graph)
        for page in graph:
            ranks[page] = 1.0 / npages  # set the initial value for all pages

        for unused in range(0, numloops):  # 10 times
            newranks = {}
            for page in graph:  # for each page
                newrank = (1 - d) / npages  # set the new rank variable to a basic value
                for node in graph:  # and for each node
                    if page in graph[node]:  # if that node is pointing to the page
                        newrank = newrank + d * ranks[node] / len(graph[node])  # set the page rank based on the rank of the node
                newranks[page] = newrank
            ranks = newranks
        return ranks


    def get_next_target(self,page):
        start_link = page.find('<a href=')

        if start_link == -1:
            return None, 0

        start_quote = page.find('"', start_link)
        end_quote = page.find('"', start_quote + 1)
        url = page[start_quote + 1 : end_quote]

        return url, end_quote
# extract the url from the page return it, plus the next index

# return the html of the page
    def get_page(self,url):
        try:
            import urllib
            return urllib.urlopen(url).read()
        except:
            return ""


    def get_all_links(self,page):
        links = []
        while True:
            url, endpos = self.get_next_target(page)
            if url:
                links.append(url)
                page = page[endpos:]
            else:
                break
        return links
# while there are urls in the page extract them and shrink the page
# return the links





# if the keyword is in the index and the url is not already added
# add the url to the keyword's list of urls
# otherwise create a new key-value pair
    def add_to_index(self,index, keyword, url):
        if keyword in index and url not in index[keyword]:
            index[keyword].append(url)
        else:
            index[keyword] = [url]


# try to add every word to the content and associate the url with it
    def add_page_to_index(self,index, url, content):
        this = content.split()
        for everyword in this:
            self.add_to_index(index, everyword, url)


    def crawl_web(self,seed):
        # Make a list with the seed as a starting element
        tocrawl = set([seed])
        crawled = set()
        graph = {}  # key - an url : value - a list of out pointing links
        index = {}  # key - a keyword : value - the links associated with it
        while tocrawl:
            page = tocrawl.pop()
            if page not in crawled:
                content = self.get_page(page)  # return the text of the page
                self.add_page_to_index(index, page, content)  # add the keywords and urls to the index
                outlinks = self.get_all_links(content)  # get all the links that the page points to
                graph[page] = outlinks  # associate the pages url with the outlinks in the graph
                tocrawl.update(outlinks)  # add all the new urls to the stack
                crawled.add(page)  # mark the page as crawled
        ranks = self.compute_ranks(graph)
        return index,ranks