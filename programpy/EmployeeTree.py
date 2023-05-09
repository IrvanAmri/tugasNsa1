import json
from anytree import Node, RenderTree

with open('dataset/source/MOCK_DATA.json') as f:
    employees = json.load(f)

employee_nodes = {}

# Create a root node for the tree
ceo = [emp for emp in employees if emp['parent'] is None][0]
root = Node(ceo['employee_name'])

# Loop through the employees and create a node for each one
for employee in employees:
    employee_id = employee['employee_id']
    employee_name = employee['employee_name']
    parent_id = employee['parent']
    if employee_id not in employee_nodes:
        if parent_id is None:
            employee_nodes[employee_id] = root
            # Employee is the CEO
            # root.children = [employee_nodes[employee_id]]
        elif (parent_id == ceo['employee_id']):
            employee_nodes[employee_id] = Node(employee_name, parent=root)
        else:
            # Employee has a parent, so add as a child node
            parent_node = employee_nodes[parent_id]
            employee_nodes[employee_id] = Node(
                employee_name, parent=parent_node)
            # parent_node.children = [employee_nodes[employee_id]]

# Print the tree
for pre, fill, node in RenderTree(root):
    print("%s%s" % (pre, node.name))


class LinkedListNode:
    def __init__(self, name):
        self.name = name
        self.next = None


head = LinkedListNode(root.name)
current = head
for pre, fill, node in RenderTree(root):
    if node is not root:
        current.next = LinkedListNode(node.name)
        current = current.next

# Print the linked list
current = head
while current is not None:
    print(current.name)
    current = current.next
