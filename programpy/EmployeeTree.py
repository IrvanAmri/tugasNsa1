import json
from anytree import Node, RenderTree
import dask
from dask.distributed import Client, progress

with open('dataset/source/MOCK_DATA.json') as f:
    employees = json.load(f)

employee_nodes = {}


@dask.delayed
def create_node(employee, parent_node):
    employee_id = employee['employee_id']
    employee_name = employee['employee_name']
    if employee_id not in employee_nodes:
        node = Node(employee_name, parent=parent_node)
        employee_nodes[employee_id] = node
        return node


# Create a Dask client
client = Client()

# Create a root node for the tree
ceo = [emp for emp in employees if emp['parent'] is None][0]
root = Node(ceo['employee_name'])

# Create delayed tasks to create nodes for each employee
node_tasks = []
for employee in employees:
    parent_id = employee['parent']
    if parent_id is None:
        node_tasks.append(create_node(employee, root))
    else:
        parent_node = employee_nodes[parent_id]
        node_tasks.append(create_node(employee, parent_node))

# Compute the delayed tasks in parallel
nodes = dask.compute(node_tasks, scheduler='distributed')[0]

# Print the tree
for pre, fill, node in RenderTree(root):
    print("%s%s" % (pre, node.name))
