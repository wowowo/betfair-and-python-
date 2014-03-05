
# a simple program that maps the words of a webpage
# to its url, then ranks these urls and when queried
# returns the highest ranking url
#imports may need to be adjusted

from webCrawl.database import Database
from webCrawl.search import Search

# create an index from a webpage
webpage = "https://www.udacity.com/cs101x/index.html"
a = Database()
index, ranks = a.crawl_web(webpage)

# get the highest rated url, associated with that keyword
b = Search()
keyword = "I"
print b.lookup(index, keyword, ranks)

