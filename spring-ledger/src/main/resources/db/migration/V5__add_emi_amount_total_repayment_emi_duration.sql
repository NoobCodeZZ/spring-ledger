ALTER TABLE loans ADD COLUMN emi_amount DECIMAL(15,2),
                  ADD COLUMN total_repayment DECIMAL(15,2),
                  ADD COLUMN emi_duration_months INT;

