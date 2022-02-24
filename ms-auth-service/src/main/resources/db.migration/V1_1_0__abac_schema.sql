/*
 *  Table - AbacRule.
 *  name - it's name of rule
 *  description - it's description of rule
 *  condition - it's rule itself on SPeL
 */
CREATE TABLE IF NOT EXISTS abac_rule
(
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    condition   TEXT         NOT NULL,

    PRIMARY KEY (name)
)