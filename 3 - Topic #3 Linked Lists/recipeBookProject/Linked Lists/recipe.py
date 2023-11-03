from dataclasses import dataclass

@dataclass
class Recipe:
    name: str
    description: str
    ingredients: list
    directions: str
    bake_time
    