# 🍨 Icecream Machine Middleware

## 🎯 Goal
Design and implement a **middleware** between the `OrderDesk` (front-end) and the `IcecreamMachine` interface.  
The middleware must translate dessert orders into preparation commands for the ice cream machine.

---

## 🧠 Task Description

Implement a middleware layer (the `IcecreamMachineController`) that connects the `OrderDesk` and the `IcecreamMachine`.

1. The controller must **interpret each dessert type** and send the correct preparation command to the ice cream machine.

2. The configuration for each dessert type (e.g. ice cream, milkshake, smoothie) should not be hard-coded directly in the controller.

3. Use **creational design patterns** to build a flexible and extensible design.

4. Each dessert type should define:

   - **Frozen ice cream mass (g)**
   - **Milk volume (ml)**
   - **Juice volume (ml)**
   - **Water volume (ml)**

5. Send the command to the machine in the format:
   ```
   "<frozenMass>g <milk>ml <juice>ml <water>ml"
   ```
   
7. Each region-specific factory must return versions of desserts with **region-dependent ingredient values**  
   (e.g. USA = larger servings, Japan = smaller portions, etc.).

---

## 🌍 Regional Recipe Table

| Region  | Ice Cream (F/M/J/W) | Milkshake (F/M/J/W) | Smoothie (F/M/J/W) | Notes |
|----------|--------------------|--------------------|--------------------|--------|
| **USA** 🇺🇸     | 250g / 30ml / 0ml / 15ml | 150g / 250ml / 0ml / 20ml | 30g / 50ml / 250ml / 60ml | Large, creamy |
| **Japan** 🇯🇵   | 120g / 15ml / 0ml / 5ml  | 70g / 120ml / 0ml / 10ml  | 15g / 20ml / 150ml / 30ml | Small, light |

---
