import re
from transaction import Transaction
from copy import copy


pattern = re.compile(r"\((?P<action>\w)\)+(?P<modifiedVar>\w):=(?P<transactionInstruction>.+)")

def parse(line):
    original_line = copy(line)
    line = "".join(line.split())    # remove all white spaces

    match = pattern.match(line)

    if match:
        action = match.group("action")
        modifiedVar = match.group("modifiedVar")
        usedVars = set()
        usedVars.update(re.findall(r'[a-zA-Z]', match.group("transactionInstruction")))


        return action, modifiedVar, usedVars
    else:
        raise ValueError(f'Line {original_line} is not correct. Make sure it looks like this: ([action 1]) [modified variable] := [used variables]. For example: (a) x := x + y')

def parseTransaction(line):
    action, modifiedVar, usedVar = parse(line)
    return Transaction(action, modifiedVar, usedVar)