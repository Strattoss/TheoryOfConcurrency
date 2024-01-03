class Transaction:
    def __init__(self, action, modifiedVar, usedVars) -> None:
        self.action = action
        self.modifiedVar = modifiedVar
        self.usedVars = usedVars

    def __str__(self) -> str:
        return f"action: {self.action}\tmodified variable: {self.modifiedVar}\tused variables: {','.join(self.usedVars)}"

def findAllActions(transactions: list[Transaction]):
    return sorted([transaction.action for transaction in transactions])
