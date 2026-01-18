UPDATE loans
SET
    emi_duration_months = years * 12,
    emi_amount = ROUND((principal * (1 + (roi * years / 100))) / (years * 12), 2),
    total_repayment = ROUND(principal * (1 + (roi * years / 100)), 2)
WHERE emi_duration_months IS NULL
   OR emi_amount IS NULL
   OR total_repayment IS NULL;