-- Check if exists, create & select database
DROP DATABASE IF EXISTS FacilityManagementSystem;
CREATE DATABASE FacilityManagementSystem;
USE FacilityManagementSystem;

-- Create tables
CREATE TABLE IF NOT EXISTS Apartment
(ApartmentID 		VARCHAR(10) NOT NULL,
Name 			      VARCHAR(50),
CONSTRAINT Facility_PK PRIMARY KEY (ApartmentID));

CREATE TABLE IF NOT EXISTS FacilityDetail
(Address 			VARCHAR(255) NOT NULL,
Zipcode             VARCHAR(6),
Age					INT,
Capatity			INT,
Parking             VARCHAR(5),
ApartmentID 		VARCHAR(10),
CONSTRAINT Facility_PK PRIMARY KEY (Address),
CONSTRAINT Unit_FK1 FOREIGN KEY(ApartmentID) REFERENCES Apartment(ApartmentID));

CREATE TABLE IF NOT EXISTS Unit
(UnitNo 			VARCHAR(10) NOT NULL,
Bedroom 			INT,
UnitStatus 			VARCHAR(20),
ApartmentID 			VARCHAR(10),
CONSTRAINT Unit_PK PRIMARY KEY (UnitNo, ApartmentID));

CREATE TABLE IF NOT EXISTS Admin
(AdminName 			VARCHAR(20) NOT NULL,
AdminLocation 		VARCHAR(50),
AdminPhoneNo		VARCHAR(20),
CONSTRAINT Admin_PK PRIMARY KEY (AdminName));

CREATE TABLE IF NOT EXISTS Staff
(StaffID 			INT NOT NULL AUTO_INCREMENT,
Lname 			VARCHAR(20),
Fname 			VARCHAR(20),
PhoneNo			VARCHAR(20),
SpecialtyDescription 		VARCHAR(20),
AdminName 			VARCHAR(20),
CONSTRAINT Staff_PK PRIMARY KEY (StaffID),
CONSTRAINT Admin_FK FOREIGN KEY(AdminName) REFERENCES Admin(AdminName));

CREATE TABLE IF NOT EXISTS Inspection
(InspectionID 			INT NOT NULL AUTO_INCREMENT,
InspectionDate                       DATE,
Technician 			VARCHAR(50),
ApartmentID 			VARCHAR(10),
CONSTRAINT Inspection_PK PRIMARY KEY (InspectionID),
CONSTRAINT Inspection_FK FOREIGN KEY(ApartmentID) REFERENCES  Apartment(ApartmentID));

CREATE TABLE IF NOT EXISTS AptUser
(UserName 			VARCHAR(20) NOT NULL,
PhoneNo			VARCHAR(20),
UnitNo               VARCHAR(10), 
ApartmentID 			VARCHAR(10),
CONSTRAINT FacilityUser_PK PRIMARY KEY (UserName),
CONSTRAINT FacilityUser_FK FOREIGN KEY(ApartmentID) REFERENCES  Apartment (ApartmentID));

CREATE TABLE IF NOT EXISTS FacilityProblem
(ProblemTypeNo 			INT NOT NULL AUTO_INCREMENT,
ProblemType               VARCHAR(30),       
CONSTRAINT FacilityProblem_PK PRIMARY KEY (ProblemTypeNo));

CREATE TABLE IF NOT EXISTS MaintenanceRequest
(RequestNo 			INT NOT NULL AUTO_INCREMENT,
RequestDate             TIMESTAMP,
ProblemTypeNo		    INT,
ProblemDescription		VARCHAR(255),
UserName 			VARCHAR(20),
CONSTRAINT MaintenanceRequest_PK PRIMARY KEY (RequestNo),
CONSTRAINT MaintenanceRequest_FK1 FOREIGN KEY(ProblemTypeNo) REFERENCES  FacilityProblem (ProblemTypeNo),
CONSTRAINT MaintenanceRequest_FK2 FOREIGN KEY(UserName) REFERENCES  AptUser (UserName));

CREATE TABLE IF NOT EXISTS Cost
(CostNo 			INT NOT NULL AUTO_INCREMENT,
LaborCost               DECIMAL(6,2),
MaterialCost			DECIMAL(6,2),
CONSTRAINT Cost_PK PRIMARY KEY (CostNo));

CREATE TABLE IF NOT EXISTS Schedule
(ScheduleNo 			INT NOT NULL AUTO_INCREMENT,
ScheduleDate            DATE,
StaffID 			    INT,
CONSTRAINT Schedule_PK PRIMARY KEY (ScheduleNo),
CONSTRAINT Schedule_FK1 FOREIGN KEY(StaffID) REFERENCES  Staff (StaffID));

CREATE TABLE IF NOT EXISTS MaintenanceOrder
(OrderNo 			INT NOT NULL AUTO_INCREMENT,
OrderDate           TIMESTAMP,
OrderStatus			VARCHAR(20),
FinishedDate        DATE,
CostNo 			    INT,
CONSTRAINT MaintenanceOrder_PK PRIMARY KEY (OrderNo),
CONSTRAINT MaintenanceOrder_FK1 FOREIGN KEY(CostNo) REFERENCES Cost (CostNo));


CREATE TABLE IF NOT EXISTS Log
(LogID			INT NOT NULL AUTO_INCREMENT,
LogDate         TIMESTAMP,       
LogInfo		VARCHAR(255),
CONSTRAINT Log_PK PRIMARY KEY (LogID));

CREATE TABLE IF NOT EXISTS Maintenance  
(MaintenanceNo           INT NOT NULL AUTO_INCREMENT,
ApartmentID 			VARCHAR(10),
UnitNo 			    VARCHAR(10),
RequestNo			INT,
ScheduleNo		    INT,
OrderNo 			INT,
ProblemTypeNo		INT,
LogID				INT,
CONSTRAINT Maintenance_PK PRIMARY KEY (MaintenanceNo),
CONSTRAINT Maintenance_FK1 FOREIGN KEY(OrderNo) REFERENCES  MaintenanceOrder (OrderNo),
CONSTRAINT Maintenance_FK2 FOREIGN KEY(RequestNo) REFERENCES  MaintenanceRequest (RequestNo),
CONSTRAINT Maintenance_FK3 FOREIGN KEY(ProblemTypeNo) REFERENCES  FacilityProblem (ProblemTypeNo),
CONSTRAINT Maintenance_FK4 FOREIGN KEY(ScheduleNo) REFERENCES  Schedule (ScheduleNo),
CONSTRAINT Maintenance_FK5 FOREIGN KEY(LogID) REFERENCES  Log (LogID));

-- Populate tables
INSERT INTO apartment(`ApartmentID`,`Name`) VALUES('APT001', 'Optima Chicago Center');
INSERT INTO apartment (`ApartmentID`, `Name`) VALUES('APT002', 'K2 Apartments');
INSERT INTO apartment (`ApartmentID`, `Name`) VALUES('APT003', 'Buena Shores');
INSERT INTO FacilityDetail(`Address`,`Zipcode`, `Age`,`Capatity`,`Parking`,`ApartmentID`) VALUES('200 E Illinois St, Chicago, IL', '60611', 3 , 325, 'Yes', 'APT001');
INSERT INTO FacilityDetail(`Address`,`Zipcode`,`Age`,`Capatity`,`Parking`,`ApartmentID`) VALUES('365 N Halsted St, Chicago, IL', '60661',3,425, 'Yes', 'APT002');
INSERT INTO FacilityDetail(`Address`,`Zipcode`,`Age`,`Capatity`,`Parking`,`ApartmentID`) VALUES('833 W Buena Ave, Chicago, IL', '60613', 47, 210, 'No', 'APT003');

INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('1A',1,'Available','APT001');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('1B',2,'Rented','APT001');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('2A',1,'Available','APT001');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('2B',2,'Rented','APT001');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('101',1,'Rented','APT002');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('102',2,'Rented','APT002');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('103',2,'Available','APT002');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('104',3,'Available','APT002');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('1001',1,'Rented','APT003');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('1002',2,'Available','APT003');
INSERT INTO unit(`UnitNo`,`Bedroom`,`UnitStatus`,`ApartmentID`) VALUES('1003',3,'Available','APT003');

INSERT INTO aptuser(`UserName`,`PhoneNo`,`UnitNo`,`ApartmentID`) VALUES('Ting Liu','3124446666','1A','APT001');
INSERT INTO aptuser(`UserName`,`PhoneNo`,`UnitNo`,`ApartmentID`) VALUES('James Wang','3124446655','1B','APT001');
INSERT INTO aptuser(`UserName`,`PhoneNo`,`UnitNo`,`ApartmentID`) VALUES('John Smith','3124345666','102','APT002');
INSERT INTO aptuser(`UserName`,`PhoneNo`,`UnitNo`,`ApartmentID`) VALUES('David Zheng','3124369087','1001','APT003');

INSERT INTO facilityproblem(`ProblemType`) VALUES('Plumbing');
INSERT INTO facilityproblem(`ProblemType`) VALUES('Electronics and appliances');
INSERT INTO facilityproblem(`ProblemType`) VALUES('Interior');
INSERT INTO facilityproblem(`ProblemType`) VALUES('Exterior');

INSERT INTO admin(`AdminName`,`AdminLocation`,`AdminPhoneNo`) VALUES('ChicagoAdmin','Chicago','844-000-1275');

INSERT INTO staff(`Lname`,`Fname`,`PhoneNo`,`SpecialtyDescription`,`AdminName`) VALUES('Steven','White','3128889900','Plumbing','ChicagoAdmin');
INSERT INTO staff(`Lname`,`Fname`,`PhoneNo`,`SpecialtyDescription`,`AdminName`) VALUES('Kevin','Clain','3127789901','Electricity','ChicagoAdmin');
INSERT INTO staff(`Lname`,`Fname`,`PhoneNo`,`SpecialtyDescription`,`AdminName`) VALUES('Mark','Zac','3128859950','Interior','ChicagoAdmin');

INSERT INTO maintenancerequest(`RequestDate`,`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES('2016-12-01', 1, 'Bathroom toilit leaking','Ting Liu');
INSERT INTO maintenancerequest(`RequestDate`,`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES('2016-03-23', 1, 'Kitchen faucet leaking','James Wang');
INSERT INTO maintenancerequest(`RequestDate`,`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES('2016-07-12', 2, 'Refrirator doesn\'t work','John Smith');
INSERT INTO maintenancerequest(`RequestDate`,`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES('2016-09-03', 3, 'Found mould on ceiling','Ting Liu');
INSERT INTO maintenancerequest(`RequestDate`,`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES('2016-12-12', 1, 'Tub dispense water too slow','David Zheng');

INSERT INTO cost(`LaborCost`,`MaterialCost`) VALUES('18','10');
INSERT INTO cost(`LaborCost`,`MaterialCost`) VALUES('18','15.5');
INSERT INTO cost(`LaborCost`,`MaterialCost`) VALUES('9','0');
INSERT INTO cost(`LaborCost`,`MaterialCost`) VALUES('54','30');
INSERT INTO cost(`LaborCost`,`MaterialCost`) VALUES('9','10.99');

INSERT INTO schedule(`ScheduleDate`,`StaffID`) VALUES('2016-12-02',1);
INSERT INTO schedule(`ScheduleDate`,`StaffID`) VALUES('2016-03-25',1);
INSERT INTO schedule(`ScheduleDate`,`StaffID`) VALUES('2016-07-14',2);
INSERT INTO schedule(`ScheduleDate`,`StaffID`) VALUES('2016-09-05',3);
INSERT INTO schedule(`ScheduleDate`,`StaffID`) VALUES('2016-12-14',1);

INSERT INTO maintenanceorder(`OrderDate`,`OrderStatus`,`FinishedDate`,`CostNo`) VALUES('2016-12-02','Finished','2016-12-03',1);
INSERT INTO maintenanceorder(`OrderDate`,`OrderStatus`,`FinishedDate`,`CostNo`) VALUES('2016-03-25','Finished','2016-03-25',2);
INSERT INTO maintenanceorder(`OrderDate`,`OrderStatus`,`FinishedDate`,`CostNo`) VALUES('2016-07-14','Finished','2016-07-14',3);
INSERT INTO maintenanceorder(`OrderDate`,`OrderStatus`,`FinishedDate`,`CostNo`) VALUES('2016-09-05','Finished','2016-09-08',4);
INSERT INTO maintenanceorder(`OrderDate`,`OrderStatus`,`FinishedDate`,`CostNo`) VALUES('2016-12-14','Finished','2016-12-14',5);


INSERT INTO maintenance(`ApartmentID`,`UnitNo`,`RequestNo`,`ScheduleNo`,`OrderNo`,`ProblemTypeNo`) VALUES('APT001','1A',1,1,1,1);
INSERT INTO maintenance(`ApartmentID`,`UnitNo`,`RequestNo`,`ScheduleNo`,`OrderNo`,`ProblemTypeNo`) VALUES('APT001','1B',2,2,2,1);
INSERT INTO maintenance(`ApartmentID`,`UnitNo`,`RequestNo`,`ScheduleNo`,`OrderNo`,`ProblemTypeNo`) VALUES('APT002','102',3,3,3,2);
INSERT INTO maintenance(`ApartmentID`,`UnitNo`,`RequestNo`,`ScheduleNo`,`OrderNo`,`ProblemTypeNo`) VALUES('APT001','1A',4,4,4,3);
INSERT INTO maintenance(`ApartmentID`,`UnitNo`,`RequestNo`,`ScheduleNo`,`OrderNo`,`ProblemTypeNo`) VALUES('APT003','1001',5,5,5,1);


