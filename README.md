# Java-CSV-Reader-Writer
Works with "standard" CSV format:
- Lines must end with the newline (\n) character;
- Optional header record with names of fields;
- Any field may be quoted (with double quotes);
- Fields containing a double-quote or commas must be quoted;
- A (double) quote character in a field must be represented by two (double) quote characters;
- If a field is quoted, the whole field is quoted (eg: no field1,"field"2,field3)
