import networkx as nx
import python_datastructures as ds
import matplotlib.pyplot as plt
import json

file = open('dataset/MOCK_DATA.json')
data = json.load(file)

# create a graph
graph = nx.Graph()

# add nodes to the graph
for i in range(len(data)):
    graph.add_node(data[i]['employee_id'])

# add edges to the graph
for i in range(len(data)):
    if (data[i]['parent']):
        graph.add_edge(data[i]['parent'], data[i]['employee_id'])

nx.draw(graph, with_labels=True)
plt.show()



'''
# print the number of nodes and edges in the graph
print("Number of edges: ", G.number_of_edges())

# print the number of connected components in the graph
print("Number of connected components: ", nx.number_connected_components(G))

# use ds.Dataset to create a dataframe
df = ds.Dataset('dataset/MOCK_DATA.json').dataframe

# print the first 5 rows of the dataframe
print(df.head())

# print the number of rows and columns in the dataframe
print(df.shape)

# print the number of unique values in the 'parent' column
print(df['parent'].nunique())

# print the number of unique values in the 'id' column
print(df['id'].nunique())

# print the number of unique values in the 'name' column
print(df['name'].nunique())

# use queue to traverse the graph
def bfs(graph, start):
    # initialize a queue
    queue = []
    # initialize a list to keep track of visited nodes
    visited = []
    # add the starting node to the queue
    queue.append(start)
    # while the queue is not empty
    while queue:
        # pop the first node from the queue
        node = queue.pop(0)
        # if the node has not been visited
        if node not in visited:
            # add the node to the list of visited nodes
            visited.append(node)
            # add the neighbors of the node to the queue
            queue.extend(graph.neighbors(node))
    # return the list of visited nodes
    return visited

# use stack to traverse the graph
def dfs(graph, start):
    # initialize a stack
    stack = []
    # initialize a list to keep track of visited nodes
    visited = []
    # add the starting node to the stack
    stack.append(start)
    # while the stack is not empty
    while stack:
        # pop the first node from the stack
        node = stack.pop()
        # if the node has not been visited
        if node not in visited:
            # add the node to the list of visited nodes
            visited.append(node)
            # add the neighbors of the node to the stack
            stack.extend(graph.neighbors(node))
    # return the list of visited nodes
    return visited

# use recursion to traverse the graph
def dfs_recursive(graph, start, visited=None):
    # if the list of visited nodes is not provided
    if visited is None:
        # initialize the list of visited nodes
        visited = []
    # add the starting node to the list of visited nodes
    visited.append(start)
    # for each neighbor of the starting node
    for neighbor in graph.neighbors(start):
        # if the neighbor has not been visited
        if neighbor not in visited:
            # recursively call dfs_recursive
            dfs_recursive(graph, neighbor, visited)
    # return the list of visited nodes
    return visited

# print the nodes visited using bfs
print("BFS: ", bfs(G, 1))
'''