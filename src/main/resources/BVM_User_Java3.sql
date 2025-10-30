CREATE DATABASE BVM_User_Java3;
GO

USE BVM_User_Java3;
GO

CREATE TABLE Users (
    Id          nvarchar(20)    PRIMARY KEY NOT NULL,  
    Password    nvarchar(50)    NULL,
    Fullname    nvarchar(50)    NULL,
    Email       nvarchar(50)    NULL,
    Admin       bit             NULL                   
);
GO

INSERT INTO Users (Id, Password, Fullname, Email, Admin) VALUES
('admin01', '123456', N'Nguyễn Văn A', 'a.nv@fpt.edu.vn', 1),
('user01', '654321', N'Trần Thị B', 'b.tb@fpt.edu.vn', 0),
('user02', '643215', N'Trần Thị C', 'b.tc@fpt.edu.vn', 0),
('user03', '453216', N'Trần Thị D', 'b.td@fpt.edu.vn', 0),
('user04', '324516', N'Trần Thị E', 'b.te@fpt.edu.vn', 0),
('user05', '164325', N'Trần Thị F', 'b.tf@fpt.edu.vn', 0),
('user06', '123465', N'Trần Thị G', 'b.tg@fpt.edu.vn', 0),
('user07', '156423', N'Trần Thị H', 'b.th@fpt.edu.vn', 0),
('user08', '234156', N'Trần Thị I', 'b.ti@fpt.edu.vn', 0),
('user09', '235461', N'Trần Thị J', 'b.tj@fpt.edu.vn', 0),
('user10', '634521', N'Trần Thị K', 'b.tk@fpt.edu.vn', 0);

USE BVM_User_Java3;
GO
ALTER TABLE Users
ALTER COLUMN Password nvarchar(60) NULL;
GO