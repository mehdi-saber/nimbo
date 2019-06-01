n = int(input())
statements = [input() for i in range(n)]
for sIndex, statement in enumerate(statements):
    if statement.startswith('print '):
        statements[sIndex] = statement.replace('print ', 'print(') + ')'
    else:
        statements[sIndex] = statement.replace(':=', '=')
[exec(statement) for statement in statements]
