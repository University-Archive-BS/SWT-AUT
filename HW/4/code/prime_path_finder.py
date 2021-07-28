import queue
import logging


class Graph:
    def __init__(self, nodes, initial_nodes, end_nodes, edges={}) -> None:
        self.nodes = nodes
        self.initial_nodes = initial_nodes
        self.edges = edges
        self.end_nodes = end_nodes

    def __repr__(self) -> str:
        return self.__str__()

    def __str__(self) -> str:
        return f'''Graph:
            nodes: {self.nodes}, 
            initial_nodes: {self.initial_nodes},
            end_nodes: {self.end_nodes},
            edges: {self.edges}
        '''

    @staticmethod
    def create_graph(file_path):
        try:
            f: _io.TextIOWrapper = open(file_path, encoding='utf-8')
            nodes = list(map(int, f.readline().split()))
            initial_nodes = list(map(int, f.readline().split()))
            end_nodes = list(map(int, f.readline().split()))
            edges = {}
            for node in nodes:
                edge = f.readline().split()
                if edge:
                    edges[node] = [int(e) for e in edge if e != '-1']
                else:
                    edges[node] = []
            return Graph(nodes, initial_nodes, end_nodes, edges)
        except Exception as e:
            logging.error(f"Failed in parsing graph because {str(e)}")
            exit(1)


def reach_head(path: list, graph: Graph):
    former_nodes = filter(lambda n: path[0] in graph.edges[n], graph.nodes)
    for n in former_nodes:
        if n not in path or n == path[-1]:
            return False
    return True


def reach_end(path: list, graph: Graph):
    later_nodes = graph.edges[path[-1]]
    for n in later_nodes:
        if n not in path or n == path[0]:
            return False
    return True


def is_extendable(path: list, graph: Graph):
    if is_prime_path(path, graph) or reach_end(path, graph):
        return False
    return True


def is_prime_path(path: list, graph: Graph):
    '''
        Check a path is prime path or not.
    '''
    if len(path) >= 2 and path[0] == path[-1]:
        return True
    elif reach_head(path, graph) and reach_end(path, graph):
        return True
    return False


def recursive_prime_path(graph: Graph, temp_paths: list, prime_paths: list):
    prime_paths.extend(filter(lambda p: is_prime_path(p, graph), temp_paths))
    temp_paths = filter(lambda p: is_extendable(p, graph), temp_paths)
    new_temp_paths = []
    for p in temp_paths:
        for nx in graph.edges[p[-1]]:
            if nx not in p or nx == p[0]:
                new_temp_paths.append(p + (nx, ))
    if len(new_temp_paths) > 0:
        recursive_prime_path(graph, new_temp_paths, prime_paths)


def get_prime_paths(graph: Graph):
    temp_paths = [(n, ) for n in graph.nodes]
    prime_paths = []
    recursive_prime_path(graph=graph, temp_paths=temp_paths,
                         prime_paths=prime_paths)
    return sorted(prime_paths, key=lambda a: (len(a), a))


def print_path(paths: list):
    for path in paths:
        print(list(path))


def is_sublist(a: list, b:list):
    if len(a) > len(b):
        return False
    for i in range(0, len(b) - len(a) + 1):
        if b[i:i+len(a)] == a:
            return True
    return False

def bfs(graph, start, ends, path):
    expand_queue = queue.Queue()
    expand_queue.put((graph, start, ends, path))
    while not expand_queue.empty():
        graph, current, ends, path = expand_queue.get()

        if current in ends:
            # route done - yield result
            return path + [current]

        if current in graph.nodes:
            # skip neighbor-less nodes
            for neighbor in graph.edges[current]:
                # put all neighbors last in line to expand
                expand_queue.put((graph, neighbor, ends, path + [current]))


def create_test_path(prime_path: list, graph: Graph):
    x = bfs(graph, graph.initial_nodes[0], [prime_path[0]], [])
    prime_path = x + prime_path[1:]
    test_path = bfs(graph, prime_path[-1], graph.end_nodes, prime_path[:-1])
    return test_path

def find_test_paths(prime_paths: list, graph: Graph):
    prime_paths.reverse()
    test_paths = []
    for prime_path in prime_paths:
        has_toured = False
        for test in test_paths:
            if is_sublist(prime_path, test):
                has_toured = True
                break
        if not has_toured:
            temp = create_test_path(list(prime_path), graph)
            if temp not in test_paths:
                test_paths.append(temp)
    return test_paths

input_file = input("Enter input file: ")
graph = Graph.create_graph(input_file)
print(graph)
prime_paths = get_prime_paths(graph=graph)
print("prime paths are:")
print_path(prime_paths)
test_cases = find_test_paths(prime_paths, graph)
print("test paths are:")
print_path(test_cases)
