def calculate_foata_normal_form(word, actions, dependency_rel):
    # using foata form algrithm from V. Diekert, Y. Métivier – Partial commutation and traces, page 10
    stacks = {action:[] for action in actions}

    def stacks_empty() :
        for stack in stacks.values():
            if len(stack) != 0:
                return False
        return True
    
    # build the stacks
    for char in word[::-1]:
        stacks[char].append(char)
        for action in actions:
            if char != action and ((char, action) in dependency_rel or (action, char) in dependency_rel):
                stacks[action].append("*")

    foata_form = []
    
    # empty the stacks by "peeling" layers
    while not stacks_empty():
        foata_layer = []
        # get exposed actions
        for action in actions:
            if len(stacks[action]) > 0 and stacks[action][-1] == action:
                foata_layer.append(stacks[action].pop())
        
        foata_form.append(foata_layer)

        # remove "*" according to actions in the peeled layer 
        for char in foata_layer:
            for action in actions:
                if char != action and ((char, action) in dependency_rel or (action, char) in dependency_rel):
                    stacks[action].pop() 
        
    for layer in foata_form:
        layer.sort()

    return foata_form

    
