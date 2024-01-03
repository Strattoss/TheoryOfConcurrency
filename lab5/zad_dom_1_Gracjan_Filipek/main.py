from parsing import parseTransaction
from transaction import findAllActions, Transaction
from dep_indep_relation import determine_dependency_independency_relations
from foata import calculate_foata_normal_form
from graph import calc_diekert_graph


# parse file and read transactions
transactions = []

# read the word
word = input()
print(f'Read word: {word.__repr__()}')
print()

# read transactions
while True:
    line = input()
    if line == "end":
         break
    
    read_transaction = parseTransaction(line)
    if read_transaction.action in [transaction.action for transaction in transactions]:
        raise ValueError(f"Transaction named '{read_transaction.action}' encountered multiple times. Make sure actions have unique names")
    transactions.append(read_transaction)

print("Parsed actions:")
for transaction in transactions:
    print(transaction)
print()

# find all action labels
actions = findAllActions(transactions)
print(f"Alphabet A (actions): {actions}\n")

# create dependency and independency relations
dependency_rel, independency_rel = determine_dependency_independency_relations(transactions)
print(f'D = {dependency_rel}\n')
print(f'I = {independency_rel}\n')

# calculate_foata_form("badacb", actions, dependent_rel)
foata_normal_form = calculate_foata_normal_form(word, actions, dependency_rel)
foata_pretty = ''.join(["(" + ",".join(layer) + ")" for layer in foata_normal_form])
print(f"Foata normal form: {foata_pretty}\n")

# build Diekert digram
print("Diekert graph:")
g = calc_diekert_graph(word, dependency_rel)
print(g)
print()