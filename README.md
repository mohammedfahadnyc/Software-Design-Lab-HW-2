# Software-Design-Lab-HW-2

 Csc 22100 : Software Design Laboratory 


1.The Problem

This is a project where we have to create an application in Java to create some specific shapes in a certain order and write a detailed report on it. This report attempts to accomplish that goal.  This project builds upon and extends the previous project that has been assigned submitted named ‘Assignment 1’.

The goal here is to create alternating patterns of sequence of concentric circles and their inscribed polygons. It comes down to developing some classes that can use other classes created in this program. We create a hierarchy super class and extend it with other sub classes. We use appropriate abstract, static, constant and default methods to specify behaviours in the class hierarchy. Using this class hierarchy and the JavaFX graphics, we must develop a program to draw geometric configuration consisting of a sequence of alternating concentric circles and their inscribed polygons, with additional requirements such as the program should run for different canvas sizes and the dimensions should be proportional to the canvas size and filled with different colors that’s predefined in the program and all object’s are processed polymorphically. In addition to that, we have draw the bounding rectangle of some objects and draw the area of their intersection.

This project heavily relies on the OOP paradigm and using java graphics (namely JavaFX) to create beautiful UI based development. We will be using Java language to develop our solution. 
We are going to implement an object-oriented solution for creating a following types of Shapes and display all of them : 

•	Line - a straight line between two points and has angle and length based on the points.
•	Rectangle - a shape with four sides and four right angles and has an area and a perimeter based on its size.
•	Oval - a smooth looking closed, plane curved shape with no straight side or no angles. It has an area, a perimeter, and a minor & a major axis.
In the previous project, we developed a small application to draw aforementioned shapes using OOP concept. We are going to enhance that application by adding following:

•	Circle - a circle is all points in the same plane that lie at an equal distance from a center point. It has a radius, an area and a perimeter.
•	Polygon - A polygon is a closed shape with straight sides. Rectangles, triangles, hexagons, and octagons are all examples of polygons. We will be using polygon consisting of 5 sides.

In addition to above two shapes, we will also add below additional features :
•	To check if the given point is lying within the shape.
•	To identify intersection area if shapes are overlapping each other. 

In short, we are going to develop a small application, using the OOP concept, with the help of the JavaFX library to create an application that will result in the aforementioned outcome.
Next section describes the details about the implementation.
 
2.Solution Method
We'll describe the our approach to the solution. The aim is to draw a shape like Line, Rectangle or Oval. 

If we look at the shapes, we found below common attributes.
1. Each shape has a color.
2. Each shape is consisting of one or more co-ordinates.
2. Each closed shape (like Rectangle, Oval, Circle, Polygon) can have an area as well as a perimeter.

We also found specific properties. Below are the examples.
1. A line is consisting of 2 co-ordinates.
2. A rectangle is consisting of 4 co-ordinates that will provide us the width as well as height of a rectangle.
3. An oval is curved shape having a single point but the way it structured (like a rectangle) will provide us the width, the height and a abscissa ( a minimum and a maximum distance from a center point).
3. A polygon is a closed shape consisting of n of sides with the same length.

With the above observations, we started by creating classes representing common thing.

1. MyColor.java: (same as Assignment 1)
This is a common property of each shape. Hence we created an enum (MyColor) which will be used to define a color of a specific shape. We defined 16 different colors in a enum (MyColor).

2. MyPoint.java: (same as Assignment 1)
A coordinate is another common poperty of each shape. It is consisting of two attributes: X and Y. MyPoint class indicates a co-ordinate in our system. It is consisting of two attributes: X and Y, required constructors and accessor/mutator methods.

3. MyShapeInterface.java: (Added for Assignment 2)
This is an interface defining the common methods which can perform certain operations upon a shape. This contains constants and appropriate, abstract, static and default methods that describe the functions and behaviors of the specific object types of the class hierarchy.  These methods are as following :
•	getMyPoints() - To be implemented by all shapes and returns all points lying with the shape.
•	getMyBoundingRectangle() - To be implemented by all shapes.
•	pointInMyShape() - To be implemented by all shapes.
•	intersectMyShapes() - A static method returning a set of points of overlapping shapes
•	drawIntersectMyShape() - A default method to draw an intersection area if shapes are overlapped.

3. MyShape.java: (Modified for Assignment 2)
MyShape implements MyShapeInterface and represents as a base for all the shapes. It is consisting of common attributes of all the shapes. That is 1. a color, 2. a co-ordinate, required constructors as well as getArea() and getPerimeter() methods. Specific shapes (like Rectangle, Oval, Polygon, Circle) extend MyShape class and override getArea() and getPerimeter() methods.

Next is to implement specific shapes like Circle and Polygon to the group of shapes (i.e. Line, Rectangle and Oval) from Assignment 1. 

4. MyLine.java: (Modified for Assignment 2)
A line is consisting of 2 coordinates and has a color. MyLine is extending MyShape, so it inherits all the properties of MyShape (like a color and a co-ordinate).
 Since line requires another co-ordinate to form a line, we added another coordinate (MyPoint). Line is an open shape, so it doesn't have an area or a perimeter, so it doesnt override getArea() and getPerimeter() method. However, a line has a length as well as an angle, so we added specific methods for a line: getLength() and getAngle().
 Thus, MyLine is consisting of all require attributes (some are inherited from MyShape), constructors and other getter/setter methods. This class is modified by adding getMyPoints() method.

Formulas:
Length:  Math.sqrt(Math.pow(X2 - X1, 2) + Math.pow(Y2 - Y1 , 2))
Angle:  Math.toDegrees(Math.atan((Y2 - Y1)/(X2 - X1)))

5. MyRectangle.java: (Modified for Assignment 2)
A rectangle is consisting of 4 coordinates and has a color. MyRectangle is extending MyShape, so it inherits all the properties of MyShape (like a color and a co-ordinate). hence MyRectangle has a co-ordinate and a color already. It now requires 3 coordinates to form a rectangle. So, we added 2 more attributes; width and height. Now we can derive rest of 3 co-ordinates using a single coordinate, a width and a height. As a rectangle is a closed shape, so it has an area or a perimeter, so getArea() and getPerimeter() method are overridden in MyRectangle. Overall, MyRectangle is consisting of all require attributes (some are inherited from MyShape), constructors and other getter/setter methods. This class is modified by adding getMyPoints() method.

Formulas:
Area:  Height * Width
Perimeter:  2 * (Height * Width)

6. MyOval.java: (Modified for Assignment 2)
An oval is consisting of a single coordinate and has a color. MyOval is extending MyShape, so it inherits all the properties of MyShape (like a color and a co-ordinate). hence it has a co-ordinate and a color already. It now to form an oval shape, it requires 2 more attributes; width and height. Like rectangle, an oval is also a closed shape, so it has an area or a perimeter, so getArea() and getPerimeter() method are overridden in MyOval. However, an oval has 2 more attributes. That is a minimum distance and a maximum distance from the center, so we added specific methods for an oval: getA() and getB(). Now, MyOval is consisting of all require attributes (some are inherited from MyShape), constructors and other getter/setter methods. This class is modified by adding getMyPoints() method.

Formulas:
Area:  Math.PI * (Height / 2) * (Width / 2)
Perimeter:  2 * Math.PI * Math.sqrt((Math.pow(Height, 2) + Math.pow(Width, 2)) / 2)
A:  Width / 2
B:  Height / 2

7. MyCircle.java: (Added for Assignment 2)
Like an oval, a circle is also consisting of a single coordinate and has a color. MyCircle is extending MyOval, hence it inherits all the properties/methods of MyOval (like a color and a co-ordinate). hence it has a co-ordinate and a color already. Circle requires an radius which is used to form and rectangle of same width and height (i.e. radius *2). A circle inherits getArea() and getPerimeter() methods from MyOval. Now, MyCircle is consisting of all require attributes (some are inherited from MyShape), constructors, other getter/setter methods and getMyPoints() method.
Formulas:
Area:  Math.PI * (Height / 2) * (Width / 2)Perimeter:  2 * Math.PI * Math.sqrt((Math.pow(Height, 2) + Math.pow(Width, 2)) / 2)
8. MyPolygon.java: (Added for Assignment 2)
A polygon is closed shape with n number of equal length sides. So, a polygon is consisting of a single coordinate, no of sides and has a color. This class extends the class MyShape. MyPolygon object is defined by the number of its equal side lengths and equal interior angles and the radius of the circle in which it’s inscribed. A polygon is slightly different due to a property on no of sides. Hence, we are required to implement every methods specific to a polygon. It has a method to calculate an area as well as a perimeter. However, it also has an additional property (An apothem) to calculate using getApothem() method. Thus, a polygon has all require attributes (some are inherited from MyShape), constructors, other getter/setter methods and getMyPoints() method.

Formulas:
Angle:  180 * (sides -2) / sides
SideLength:  2 * Radius * Math.sin(Math.PI/sides)
Area:  (Math.pow(SideLength,2) * sides) (4 * Math.tan(Math.PI/sides))
Perimeter:  SideLength * sides
Apothem:  Radius * Math.cos(Math.PI/sides)

Above classes are to represent Shapes in OOP system. We also developed below supporting class to display the results on the screen.

9. MyCanvas.java: (same as Assignment 1)
This is an entry point of the application. It is build upon JavaFX framework. It launches the application, prompts user for an input and displays the results (Line, Rectangle and Oval) on the screen. 
Note: Result section doesn't include results from this class.

10. MyCanvas1.java: (Added for Assignment 2)
This is a new entry point of the application. Like MyCanvas, this is also build upon JavaFX framework. It launches the application, prompts user for an input and displays the results (Circle and Polygon) on the screen (refer section Result 1). 

11. MyCanvas2.java: (Added for Assignment 2)
This is another entry point of the application. This application helps to verify the methods that are part of MyShapeInterface. It displays 6 shapes in pairs overlapping each other and displays overlapped area in different color on the screen (refer section Result 2).

Flow chart and Class Diagram : Here is a simple flow chart and class diagram for our program :
Flow Chart:
Below is the flow diagram of an application.

 

Class Diagram:
Below is the modified class diagram for our application.

 
Tools and Technology: 
Java - All the classes are built using Java language only.
JavaFX - Alert/ButtonType - component used to prompt user if she/he wants to use default size canvas
JavaFX - ChoiceDialog - component used to prompt user with a dropdown with different sizes for a canvas
JavaFX - GraphicsContext - component used for drawing of shapes like a line, a rectangle or an oval.

3.Codes developed 

The full code is uploaded to this repo.


4.Output produced for the tasks indicated:
Result 1: 
Printing sequence of alternating circles and inscribed polygons that’s applicable to canvases of  variable size and height.
Use Case 1: User opts to use default canvas size (1000x600).


 

 
 
Use Case 2: 
User opts to use different canvas size (333x200).

 

             

 
Result 2: 
Drawing the intersection areas of : two rectangle objects, a MyRectangle object and a MyCircle object, and a  MyRectangle object and a MyPolygon object.
Use Case 1: User opts to use default canvas size (1000x600).

 

 
 
Use Case 2: User opts to use different canvas size (333x200).

 

               


 
![image](https://user-images.githubusercontent.com/54411378/138384682-21dd81cd-c43d-45a6-9011-f5295149305a.png)
