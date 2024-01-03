from copy import copy

class Node:
    def __init__(self, id, label) -> None:
        self.id = id
        self.label = label
        self.neighbors: set[Node] = set()

    def add_neighbor(self, neighbor):
        self.neighbors.add(neighbor)

    def remove_neighbor(self, neighbor):
        self.neighbors.remove(neighbor)

    def has_path_to(self, target):
        if self.id == target.id:
            return True
        
        stack: set[Node] = set((self,))
        visited = []

        while len(stack) > 0:            
            node = stack.pop()

            if node.id == target.id:
                return True
        
            if node not in visited:
                visited.append(node)
                stack.update(set(node.neighbors))
        
        return False
    
    def __str__(self) -> str:
        return f'({self.id}, {self.label})'

def calc_diekert_graph(word, dependency_rel):
    nodes = [Node(i+1, label) for i, label in enumerate(word)]

    # construct full dependency graph
    for i, n1 in enumerate(nodes):
        for n2 in nodes[i+1:]:
            if n1.label == n2.label or (n1.label, n2.label) in dependency_rel or (n2.label, n1.label) in dependency_rel:
                n1.add_neighbor(n2)

    # minimise it into Diekert graph
    for node in copy(nodes):
        for neighbor in copy(node.neighbors):
            for neighbour2 in copy(node.neighbors):
                if neighbor != neighbour2 and neighbour2.has_path_to(neighbor):
                    node.remove_neighbor(neighbor)
                    break
    
    return build_graph_dot_format(nodes)
    

def build_graph_dot_format(nodes: list[Node]):
    graph_builder = ["digraph Diekert {\n"]
    for node in nodes:
        for neighbour in node.neighbors:
            graph_builder.append(f'\t{node.id} -> {neighbour.id}\n')
    for node in nodes:
        graph_builder.append(f'\t{node.id}[ label={node.label} ]\n')
    graph_builder.append("}")
    
    return "".join(graph_builder)
