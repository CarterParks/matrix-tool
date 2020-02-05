# matrix-tool
A shell Tool designed to make it easier to work with matrices.
## Current operations:

* Add, Subtract
* Multiply by Scalar
* Multiply by Matrix
* Transpose
* View, New Matrix, Switch Matrix
* Vector Cross Product
* Upper Triangular
* Determinant

All operations are performed with the current Matrix as the first term in the operation. Current Matrix can be switched between created matrices. Operation menu will show a list of valid matrices that the operation can be performed upon. For example: Say the current Matrix is the 2 x 2 *Matrix A*. Also extant are 2 x 1 *Matrix B* and 1 x 3 *Matrix C*. Selecting the **Multiply by Matrix** option on Matrix A would list only Matrix B as an available option. Vector Cross Product makes a similar distinction.

## Future Features
* Editing Matrices
* Vector Magnitude
* Vector Unitification (Make Unit vector from vector)
* Cramer's Rule for Systems of equations
* Lower Triangular
* Read .csv or other data sets
* Vector dot product
* Span
## Future software tweaks
* Remove menu, and command system
* Separate option classes and feature methods
* Consolidate quiet methods by putting a private 'math'-od in classes with quiet method
* Replace quiet()s with crunch()s? Vice-versa?
