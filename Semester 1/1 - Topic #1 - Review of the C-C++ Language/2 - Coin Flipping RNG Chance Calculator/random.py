import os
import random
import sys
import secrets
"""
  Generates a Random Number being either 0 or 1
  0 = Heads
  1 = Tails
 *************************************************
 
  Author: Aaron Buckner
  License: MIT
  Start Date: 9/7/23 @ 12:55 PM CST
  Last Edited:  9/12/23 @ 05:12 PM CST
 
"""
# Python has multiple ways of generating randomness:
# 1. pseudo-random number generation can be done with the random class
# 2. Cryptographically Secure Random Number Generation can be done 
#   with the os.urandom() function, and can be used for cryptographic applications
#   change the following variable from false to true to see os.urandom vs normal PRNGs
#
# Note from my limited testing that it wasn't more or less random vs using random.randInt

cryptographic_number_generation = False

class PseudoRandomNumbers:
    def __init__(self):
        self.heads = 0
        self.tails = 0
        self.flips = 0
    def flip(self, flips):
        self.flips = flips
        for i in range(flips):
            randNum = random.randint(0, 1)
            if (randNum == 0):
                self.heads = self.heads + 1
            elif (randNum == 1):
                self.tails = self.tails + 1
            else:
                raise Exception("A Random Number was implemented which was not 0 or 1")
        print("Heads/flips: " + str((self.heads/self.flips)*100) + "%") 
        print("Tails/flips: " + str((self.tails/self.flips)*100) + "%")
        print("Heads Total: " + str(self.heads))
        print("Tails Total: " + str(self.tails))
        print("Number of Flips: " + str(self.flips))

class CryptographicNumberGeneration:
    def __init__(self):
        self.heads = 0
        self.tails = 0
        self.flips = 0
        
    def flip(self, flips):
        print("Using secrets.SystemRandom()")
        self.flips = flips
        for i in range(flips):
            randNum = secrets.SystemRandom().randint(0, 1)
            if (randNum == 0):
                self.heads = self.heads + 1
            elif (randNum == 1):
                self.tails = self.tails + 1
            else:
                raise Exception("A Random Number was implemented which was not 0 or 1")
        print("Heads/flips: " + str((self.heads/self.flips)*100) + "%") 
        print("Tails/flips: " + str((self.tails/self.flips)*100) + "%")
        print("Heads Total: " + str(self.heads))
        print("Tails Total: " + str(self.tails))
        print("Number of Flips: " + str(self.flips))


if __name__ == "__main__":
    if (cryptographic_number_generation == False):
        prng = PseudoRandomNumbers()
        prng.flip(999999)
    else:
        prng = CryptographicNumberGeneration()
        prng.flip(999999)
    
    
