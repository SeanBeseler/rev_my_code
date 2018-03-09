-----2.1----------------

SELECT *
FROM EMPLOYEE;

SELECT *
FROM EMPLOYEE
WHERE LASTNAME = 'King';

SELECT *
FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

-----2.2----------------


SELECT TITLE
FROM ALBUM
ORDER BY title DESC;

SELECT FIRSTNAME, LASTNAME 
FROM CUSTOMER
ORDER BY CITY;

-----2.3----------------



INSERT INTO GENRE (GENREID, NAME)
VALUES( 10101010101, 'SEAN');

INSERT INTO GENRE (GENREID, NAME)
VALUES(10101010102, 'BESELER');
COMMIT;

INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
VALUES (1010101, 'BESELER', 'SEAN', 'SALES', NULL, TO_DATE('1991-04-02', 'YYYY-MM-DD'), TO_DATE('2017-04-02', 'YYYY-MM-DD'),'19741 NE 191ST', 'WOODINVILLE', 'WA', 'USA', '98077', '12062409237', NULL, 'SEANWBESELER@GAMIL.COM');

INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL)
VALUES (1010102, 'BESELER', 'SEAN', 'SALES', NULL, TO_DATE('1991-04-02', 'YYYY-MM-DD'), TO_DATE('2017-04-02', 'YYYY-MM-DD'),'19741 NE 191ST', 'WOODINVILLE', 'WA', 'USA', '98077', '12062409237', NULL, 'SEANWBESELER@GAMIL.COM');
COMMIT;


INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
VALUES (1010101, 'SEAN', 'BESELER', NULL,'19741 NE 191ST', 'WOODINVILLE', 'WA', 'USA', '98077', '12062409237', NULL, 'SEANWBESELER@GAMIL.COM', 1);

INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
VALUES (1010102, 'SEAN', 'BESELER', NULL,'19741 NE 191ST', 'WOODINVILLE', 'WA', 'USA', '98077', '12062409237', NULL, 'SEANWBESELER@GAMIL.COM', 1);
COMMIT;


-----2.4----------------


UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';



-----2.5----------------


SELECT *
FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';


-----2.6----------------



SELECT *
FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;


SELECT *
FROM EMPLOYEE
WHERE HIREDATE BETWEEN DATE ' 2003- 06-01' AND DATE '2004-03-1';

-----2.7----------------

ALTER TABLE InvoiceLine
    DROP CONSTRAINT FK_INVOICELINEINVOICEID;
    
ALTER TABLE InvoiceLine
    ADD CONSTRAINT FK_INVOICELINEINVOICEID
    FOREIGN KEY (InvoiceId) REfERENCES Invoice(InvoiceId) ON DELETE CASCADE;
    
ALTER TABLE Invoice
    DROP CONSTRAINT FK_INVOICECUSTOMERID;
      
ALTER TABLE Invoice
    ADD CONSTRAINT FK_INVOICECUSTOMERID
    FOREIGN KEY (CustomerId) REfERENCES Customer(CustomerId) ON DELETE CASCADE;
    
    
DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';



-----7.1----------------


SELECT CUSTOMER.*, CUSTOMER.*, INVOICE.*
FROM INVOICE
INNER JOIN CUSTOMER ON INVOICE.INVOICEID = CUSTOMER.CUSTOMERID;

-----7.2----------------


SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM INVOICE
FULL OUTER JOIN CUSTOMER ON INVOICE.INVOICEID=CUSTOMER.CUSTOMERID;

-----7.3----------------


SELECT ARTIST.NAME, ALBUM.TITLE
FROM ALBUM
RIGHT JOIN ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID;

-----7.4----------------


SELECT ALBUM.*, ARTIST.*
FROM ALBUM
CROSS JOIN ARTIST
ORDER BY ARTIST.NAME;

-----7.5----------------

SELECT A.Employeeid AS EMPLOYEE1, B.Employeeid AS EMPLOYEEBOSS
FROM EMPLOYEE A
JOIN EMPLOYEE B ON A.REPORTSTO = B.Employeeid;

-----4.1----------------

CREATE OR REPLACE PROCEDURE get_all_employees(cursorParam OUT SYS_REFCURSOR)
IS

BEGIN
   OPEN cursorParam FOR
   SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END get_all_employees;
/
var c REFCURSOR;
EXECUTE get_all_employees(:c);
PRINT c;
-----4.2----------------

CREATE OR REPLACE PROCEDURE update_employee (employee_ID IN NUMBER, manager IN NUMBER )
IS
BEGIN
    UPDATE EMPLOYEE
    SET REPORTSTO = manager
    WHERE EMPLOYEEID = employee_ID;
END update_employee;
/


CREATE OR REPLACE PROCEDURE get_employee_boss (employee_ID IN NUMBER, man OUT SYS_REFCURSOR )
IS
BEGIN
    OPEN man FOR
    SELECT B.REPORTSTO AS EMPLOYEEBOSS_ID
    FROM EMPLOYEE B
    WHERE B.EMPLOYEEID = employee_ID;
END get_employee_boss;
/

var a REFCURSOR;
EXECUTE get_employee_boss(1010101, :a);
PRINT a;

EXECUTE update_employee(1010101, 1);

-----4.3----------------

CREATE OR REPLACE PROCEDURE get_customer_info (customer_ID IN NUMBER, infor OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN infor FOR
    SELECT B.FIRSTNAME AS firstName, B.LASTNAME AS lastName, B.COMPANY as company
    FROM CUSTOMER B
    WHERE B.CUSTOMERID = customer_ID;
END get_customer_info;
/

var b REFCURSOR;
EXECUTE get_customer_info(1, :b);
PRINT b;

commit;


-----5.0----------------

CREATE OR REPLACE PROCEDURE remove_invoice (invoice_ID IN NUMBER)
IS
BEGIN
   DELETE FROM INVOICE
    WHERE INVOICE= invoice_ID;
END remove_invoice;
/

EXECUTE remove_invoice(2);


CREATE OR REPLACE PROCEDURE add_customer (c_CUSTOMERID IN NUMBER, c_FIRSTNAME IN VARCHAR2, c_LASTNAME IN VARCHAR2, c_COMPANY IN VARCHAR2, c_ADDRESS IN VARCHAR2, c_CITY IN VARCHAR2, c_STATE IN VARCHAR2, c_COUNTRY IN VARCHAR2, c_POSTALCODE IN VARCHAR2, c_PHONE IN VARCHAR2, c_FAX IN VARCHAR2, c_EMAIL IN VARCHAR2, c_SUPPORTREPID IN NUMBER)
IS
BEGIN
    INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID)
    VALUES (c_CUSTOMERID, c_FIRSTNAME, c_LASTNAME,  c_COMPANY, c_ADDRESS, c_CITY, c_STATE, c_COUNTRY, c_POSTALCODE, c_PHONE, c_FAX,  c_EMAIL, c_SUPPORTREPID);
    commit;
END add_customer;
/

EXECUTE add_customer(1010104, 'SEAN', 'BESELER', NULL,'19741 NE 191ST', 'WOODINVILLE', 'WA', 'USA', '98077', '12062409237', NULL, 'SEANWBESELER@GAMIL.COM', 1);

-----6.1----------------

CREATE OR REPLACE TRIGGER after_employee
AFTER INSERT ON EMPLOYEE FOR EACH ROW

DECLARE 
    v_username varchar(10);
BEGIN
    SELECT user INTO v_username
    FROM dual;
END;
/


CREATE OR REPLACE TRIGGER after_album
AFTER UPDATE ON ALBUM FOR EACH ROW

DECLARE 
    v_username varchar(10);
BEGIN
    SELECT user INTO v_username
    FROM dual;
END;
/


CREATE OR REPLACE TRIGGER after_customer
AFTER DELETE ON CUSTOMER FOR EACH ROW

DECLARE 
    v_username varchar(10);
BEGIN
    SELECT user INTO v_username
    FROM dual;
END;
/



--------3.1------
create or replace FUNCTION get_current_time
return TIMESTAMP IS l_systimestamp TIMESTAMP;
BEGIN
    SELECT systimestamp
    INTO l_systimestamp
    FROM dual;
    RETURN l_systimestamp;
END;



SELECT get_current_time() FROM DUAL;

create or replace FUNCTION get_len
return NUMBER IS len NUMBER;
BEGIN 
    SELECT LENGTH ('COOl')
    INTO len
    FROM DUAL;
    RETURN len;
END;
/

SELECT get_len FROM DUAL;

------3.2-------
create or replace FUNCTION get_ave
return NUMBER IS tot NUMBER;
BEGIN 
    SELECT AVG(TOTAL)
    INTO tot
    FROM INVOICE;
    RETURN tot;
END;
/

SELECT get_ave FROM DUAL;


create or replace FUNCTION get_max
return NUMBER IS ax NUMBER;
BEGIN 
    SELECT MAX(UNITPRICE)
    INTO ax
    FROM TRACK;
    RETURN ax;
END;
/

SELECT get_max FROM DUAL
-------3.3--------
create or replace FUNCTION get_ave_inline
return NUMBER IS tot NUMBER;
BEGIN 
    SELECT AVG(UNITPRICE)
    INTO tot
    FROM INVOICELINE;
    RETURN tot;
END;
/

SELECT get_ave_inline FROM DUAL;


-----3.4------
SELECT *
FROM EMPLOYEE
WHERE to_char(BIRTHDATE, 'yyyy-mm-dd') < '31-DEC-68';

create or replace FUNCTION get_emp
return SYS_REFCURSOR AS tot SYS_REFCURSOR;
BEGIN 
    SELECT *
    INTO tot
    FROM EMPLOYEE
    WHERE to_char(BIRTHDATE, 'yyyy-mm-dd') < '31-DEC-68';
    RETURN tot;
END;
/

SELECT get_emp FROM DUAL;