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
RequestNo 			    INT,
ScheduleDate            DATE,
StaffID 			    INT,
CONSTRAINT Schedule_PK PRIMARY KEY (ScheduleNo),
CONSTRAINT Schedule_FK1 FOREIGN KEY(StaffID) REFERENCES  Staff (StaffID),
CONSTRAINT Schedule_FK2 FOREIGN KEY(RequestNo) REFERENCES  MaintenanceRequest (RequestNo));

CREATE TABLE IF NOT EXISTS MaintenanceOrder
(OrderNo 			INT NOT NULL AUTO_INCREMENT,
OrderDate           TIMESTAMP,
ScheduleNo 			INT,
OrderStatus			VARCHAR(20),
FinishedDate        DATE,
CostNo 			    INT,
CONSTRAINT MaintenanceOrder_PK PRIMARY KEY (OrderNo),
CONSTRAINT MaintenanceOrder_FK1 FOREIGN KEY(CostNo) REFERENCES Cost (CostNo),
CONSTRAINT MaintenanceOrder_FK2 FOREIGN KEY(ScheduleNo) REFERENCES Schedule (ScheduleNo));

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

INSERT INTO facilityproblem(`ProblemType`) VALUES('Plumbing');
INSERT INTO facilityproblem(`ProblemType`) VALUES('Electronics and appliances');
INSERT INTO facilityproblem(`ProblemType`) VALUES('Interior');
INSERT INTO facilityproblem(`ProblemType`) VALUES('Exterior');

INSERT INTO maintenancerequest(`RequestDate`,`ProblemTypeNo`,`ProblemDescription`,`UserName`) VALUES(CURRENT_DATE, 1, 'Bathroom toilit leaking','Ting Liu');

INSERT INTO maintenance(`ApartmentID`,`UnitNo`,`RequestNo`) VALUES('APT001', '1A', 1);
