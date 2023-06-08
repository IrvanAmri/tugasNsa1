import networkx as nx
import matplotlib.pyplot as plt
from collections import deque
import json
import dask
from dask.distributed import Client, progress

file = open('dataset/source/MOCK_DATA.json')
data = json.load(file)

graph = nx.Graph()
labels = {}

# add nodes to the graph
for i in range(len(data)):
    graph.add_node(data[i]['employee_id'])
    labels[data[i]['employee_id']] = data[i]['employee_name']

# add edges to the graph
for i in range(len(data)):
    if (data[i]['parent']):
        graph.add_edge(data[i]['parent'], data[i]['employee_id'])

nx.draw(graph, labels=labels, with_labels=True)


@dask.delayed
def dfs_traversal(graph, start):
    visited = set()
    stack = [start]
    dfs_tree = nx.Graph()
    while stack:
        vertex = stack.pop()
        if vertex not in visited:
            print(str(vertex) + ' ', end='')
            visited.add(vertex)
            dfs_tree.add_node(vertex)
            dfs_tree.add_edge(vertex, start) if vertex != start else None
            stack.extend(reversed(list(graph.neighbors(vertex))))
    return dfs_tree


@dask.delayed
def bfs_traversal(graph, start):
    visited = set()
    queue = deque([start])
    bfs_tree = nx.Graph()
    while queue:
        vertex = queue.popleft()
        if vertex not in visited:
            print(str(vertex) + ' ', end='')
            visited.add(vertex)
            bfs_tree.add_node(vertex)
            bfs_tree.add_edge(vertex, start) if vertex != start else None
            queue.extend(graph.neighbors(vertex))
    return bfs_tree


# Create a Dask client
client = Client()

# Create delayed tasks for DFS and BFS traversals
dfs_task = dfs_traversal(graph, 1)
bfs_task = bfs_traversal(graph, 1)

# Compute the delayed tasks in parallel
dfs_result = dask.compute(dfs_task, scheduler='distributed')[0]
bfs_result = dask.compute(bfs_task, scheduler='distributed')[0]

# Print the results
print('DFS Traversal : ')
print(dfs_result)
print()

print('BFS Traversal : ')
print(bfs_result)

plt.show()
