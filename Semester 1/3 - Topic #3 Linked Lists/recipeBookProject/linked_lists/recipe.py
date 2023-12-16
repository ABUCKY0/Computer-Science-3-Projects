"""Recipe Module
"""
from dataclasses import dataclass


@dataclass
class Recipe:
    """The Recipe Class, containing, a name, description, ingredients list, directions, and more
    """
    name: str
    description: str # short description
    ingredients: list # list of strings
    directions: list # list of strings
    bake_time: str # in minutes
    servings: int # number of servings
    serving_size: float # in cups
    def __repr__(self):
        string = " Name: " + str(self.name) + "\n"
        string += " Description: " + str(self.description) + "\n"
        ingredients = ""
        x = 0
        for i in self.ingredients:
            x += 1
            ingredients += "    " + str(x) + ": " + i + "\n"
        string += " Ingredients: " + ingredients + "\n"
        directions = ""
        x = 0
        for i in self.directions:
            x += 1
            directions += str(x) + ": " + i + "\n"
        string += " Directions: " + str(directions) + "\n"
        string += " Bake Time: " + str(self.bake_time) + "\n"
        string += " Servings: " + str(self.servings) + "\n"
        string += " Serving Size: " +  str(self.serving_size)
        return string
    