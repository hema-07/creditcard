DROP TABLE IF EXISTS CREDIT_CARD;

CREATE TABLE CREDIT_CARD (
  CREDIT_ACCOUNT_ID VARCHAR(35) PRIMARY KEY NOT NULL,
  CREDIT_CARD_NUMBER BIGINT(19) NOT NULL,
  CREDIT_CARD_HOLDER_NAME VARCHAR(50) NOT NULL,
  CREDIT_LIMIT DECIMAL(15,2) DEFAULT 0 NOT NULL,
  CREDIT_BALANCE DECIMAL(15,2) DEFAULT 0 NOT NULL
);

INSERT INTO CREDIT_CARD (CREDIT_ACCOUNT_ID, CREDIT_CARD_NUMBER, CREDIT_CARD_HOLDER_NAME, CREDIT_LIMIT, CREDIT_BALANCE) VALUES
  (1, 79927398713, 'Hema', 200.00, 150.00),
   (2, 79929398919, 'Tamil', 1200.00, 300.00),
   (3, 79927378111, 'Maran', 200.00, 150.00);