from linked_lists.singly_linked_list import SinglyLinkedList as LinkedList
from filesaving import filesaving
from linked_lists.recipe import Recipe
import os

def main():
    """The main function for the program
    """
    sd = filesaving.Savedata()
    if os.path.exists("recipeBookProject/recipes.bin"):
        recipes = sd.load_file()
        print(type(recipes))
    else:
        recipes = LinkedList()
    while True:
        print("1. Add a recipe")
        print("2. Remove a recipe")
        print("3. View all recipes")
        print("4. Exit")
        choice = input("What would you like to do? ")
        if choice == "1":
            name = str(input("What is the name of the recipe? "))
            desc = str(input("Describe the recipe: "))
            print("What are the ingredients? (Press enter again after the last line to continue): ")
            ingredients = []
            i = 0
            while True:
                i += 1
                try:
                    inp = input(str(i)+ ": ")
                    if len(inp) == 0:
                        raise EOFError
                    ingredients.append(inp)
                except EOFError:
                    break
                
            print("How do you cook it? (Press enter again after the last line to continue): ")
            directions = []
            i = 0
            while True:
                i += 1
                try:
                    inp = input(str(i)+ ": ")
                    if len(inp) == 0:
                        raise EOFError
                    directions.append(inp)
                except EOFError:
                    break
            bake_time = str(input("How long do you cook this for in minutes?: "))
            servings = int(input("How many people does it feed?: "))
            serving_size = float(input("How many cups are in each serving?: "))
            
            
            r = Recipe(name, desc, ingredients, directions, bake_time, servings, serving_size)
            if recipes.size > 0:
                recipes.add_back(r)
            else:
                recipes.add_front(r)
        elif choice == "2":
            print(recipes)
            choice = input("What recipe would you like to remove? ")
            recipes.delete(int(choice))
        elif choice == "3":
            print(recipes)
        elif choice == "4":
            sd.save_file("recipeBookProject/recipes.bin", recipes)
            break
        else:
            print("Invalid input")
            
if __name__ == "__main__":
    main()