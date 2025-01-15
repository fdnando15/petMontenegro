-- CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;
ALTER TABLE pet_owners ALTER COLUMN id RESTART WITH 100;
ALTER TABLE clinic_owner ALTER COLUMN id RESTART WITH 100;
ALTER TABLE pets ALTER COLUMN id RESTART WITH 100;



-- Insertar datos en la tabla CLINIC_OWNER
INSERT INTO CLINIC_OWNER (ID, BIRTH_DAY, EMAIL, NAME, PASSWORD, PHONE, ROLE,  ADDRESS) VALUES
(1, '1975/03/15', 'jhon1@gmail.com', 'Dr. John Smith', '123', '123456678', 'CLINIC_OWNER', '123 Main Street');
INSERT INTO CLINIC_OWNER (ID, BIRTH_DAY, EMAIL, NAME, PASSWORD, PHONE, ROLE, ADDRESS) VALUES
(2, '1980/05/20', 'estrada@gmail.com', 'Dr. Estrada', '123', '123456678', 'CLINIC_OWNER','123 Main Street');

-- Insertar datos en la tabla PET_OWNERS
INSERT INTO PET_OWNERS (ID, BIRTH_DAY, EMAIL, NAME, PASSWORD, PHONE, ROLE, CLINIC_OWNER_ID) VALUES
(1, '1990/05/12', 'alice@gmail.com', 'Alice Brown','123', '123456789', 'PET_OWNER' , 1);
INSERT INTO PET_OWNERS (ID, BIRTH_DAY, EMAIL, NAME, PASSWORD, PHONE, ROLE, CLINIC_OWNER_ID) VALUES
(2, '1995/07/25', 'bob@gmail.com', 'Bob White','123', '123456789', 'PET_OWNER', 1);
INSERT INTO PET_OWNERS (ID, BIRTH_DAY, EMAIL, NAME, PASSWORD, PHONE, ROLE, CLINIC_OWNER_ID) VALUES
(3, '1998/09/30', 'raul@gmail.com', 'Raul Black','123', '123456789', 'PET_OWNER', 2);


-- Insertar datos en la tabla PETS
INSERT INTO PETS (ID, BIRTH_DAY, NAME, PET_OWNER_ID) VALUES
(1, '2020/06/15', 'Buddy', 1);
INSERT INTO PETS (ID, BIRTH_DAY, NAME, PET_OWNER_ID) VALUES
(2, '2018/11/05', 'Bella', 1);
INSERT INTO PETS (ID, BIRTH_DAY, NAME, PET_OWNER_ID) VALUES
(3, '2019/02/14', 'Max', 2);
INSERT INTO PETS (ID, BIRTH_DAY, NAME, PET_OWNER_ID) VALUES
(4, '2021/04/20', 'Daisy', 3);


