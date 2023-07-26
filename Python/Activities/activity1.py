name = input( "What is your name: " )
age = int( input( "How old are you: " ) )
year = str ((2023 - age) + 100) # using typecast made it string to get it printed in enxt line
print( name + " will be 100 years old in the year " + year )