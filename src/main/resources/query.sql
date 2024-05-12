drop database Salon;

create database Salon;

use Salon;

CREATE TABLE Users(
                      User_ID VARCHAR (20) PRIMARY KEY,
                      Name VARCHAR (20),
                      Password VARCHAR (20)
);

CREATE TABLE Customer (
                          Phone VARCHAR (20) PRIMARY KEY,
                          Name VARCHAR (20),
                          Address VARCHAR (20)
);

CREATE TABLE Supplier (
                          Sup_Id VARCHAR (10) PRIMARY KEY,
                          Phone INT (20),
                          Name VARCHAR (20)
);

CREATE TABLE Employee (
                          Emp_Id VARCHAR (10) PRIMARY KEY,
                          Name VARCHAR (20),
                          Address VARCHAR (20),
                          Phone VARCHAR (20),
                          Salary DECIMAL (10,2)
);

CREATE TABLE Item (
                      Item_Id VARCHAR (10) PRIMARY KEY,
                      Description VARCHAR (20),
                      Price DECIMAL NOT NULL,
                      Qty INT (10),
                      Sup_Id VARCHAR (10),
                      FOREIGN KEY (Sup_Id) REFERENCES Supplier(Sup_Id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Orders (
                        Order_Id VARCHAR (10) PRIMARY KEY,
                        Date DATE,
                        Amount DECIMAL(10, 2),
                        Status VARCHAR (20),
                        Phone VARCHAR (20),
                        FOREIGN KEY (Phone) REFERENCES Customer(Phone) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Payment (
                         Pay_Id VARCHAR (10) PRIMARY KEY,
                         Amount DECIMAL (10, 2),
                         Date DATE,
                         Order_Id VARCHAR (10),
                         FOREIGN KEY (Order_Id) REFERENCES Orders(Order_Id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Service (
                         S_Id VARCHAR (10) PRIMARY KEY,
                         Name VARCHAR(30),
                         Price DECIMAL(10, 2),
                         Emp_Id VARCHAR (10),
                         FOREIGN KEY (Emp_Id) REFERENCES Employee(Emp_Id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Attendance (
                            Attend_Id VARCHAR (10) PRIMARY KEY,
                            Date DATE,
                            Status VARCHAR (10),
                            Emp_Id VARCHAR (10),
                            FOREIGN KEY (Emp_Id) REFERENCES Employee(Emp_Id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Salary (
                        Salary_Id VARCHAR (10) PRIMARY KEY,
                        Amount DECIMAL (10, 2),
                        Year YEAR,
                        Month TINYINT,
                        Emp_Id VARCHAR (10),
                        FOREIGN KEY (Emp_Id) REFERENCES Employee(Emp_Id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Item_orders (
                             Io_Id VARCHAR (10) PRIMARY KEY,
                             Order_Id VARCHAR (10),
                             Item_Id VARCHAR (10),
                             FOREIGN KEY (Order_Id) REFERENCES Orders(Order_Id) ON UPDATE CASCADE ON DELETE CASCADE,
                             FOREIGN KEY (Item_Id) REFERENCES Item(Item_Id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Service_orders (
                                SO_Id VARCHAR (10) PRIMARY KEY,
                                S_Id VARCHAR (10),
                                Order_Id VARCHAR (10),
                                FOREIGN KEY (S_Id) REFERENCES Service(S_Id) ON UPDATE CASCADE ON DELETE CASCADE,
                                FOREIGN KEY (Order_Id) REFERENCES Orders(Order_Id) ON UPDATE CASCADE ON DELETE CASCADE
);

