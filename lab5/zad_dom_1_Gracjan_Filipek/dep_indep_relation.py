from transaction import Transaction

def determine_dependency_independency_relations(transactions: dict):
    dependency_rel = []
    independency_rel = []

    for tr1 in transactions:
        for tr2 in transactions:
            if tr1.modifiedVar == tr2.modifiedVar or tr2.modifiedVar in tr1.usedVars or tr1.modifiedVar in tr2.usedVars:
                dependency_rel.append((tr1.action, tr2.action))
            else:
                independency_rel.append((tr1.action, tr2.action))

    return dependency_rel, independency_rel
    
