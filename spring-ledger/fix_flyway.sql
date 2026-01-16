-- Delete all migration records from Flyway schema history
-- This will allow Flyway to re-apply all migrations fresh
DELETE FROM flyway_schema_history;

-- Alternative: Just delete the problematic V1 record
-- DELETE FROM flyway_schema_history WHERE version = '1';

