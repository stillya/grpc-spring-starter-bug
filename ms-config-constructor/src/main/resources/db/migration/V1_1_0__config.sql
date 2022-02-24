/*
 * Config related entities
 */

CREATE TYPE config_type AS ENUM ('CT_SCHEMA', 'CT_JSON_EDITOR', 'CT_JSON_CONSTRUCTOR', 'CT_EXTERNAL_SYSTEM', 'CT_SMS_TEMPLATE', 'CT_EMAIL_TEMPLATE');
CREATE TYPE config_category AS ENUM ('CC_PLATFORM_CREDENTIALS', 'CC_PLATFORM_SETTING', 'CC_BUSINESS_MODULE_SETTING');

/*
 * Table - configs.
 */
CREATE TABLE IF NOT EXISTS config
(
    -- general --
    config_id          UUID            NOT NULL,
    creation_date      TIMESTAMP       NOT NULL DEFAULT NOW(),
    updating_date      TIMESTAMP,
    creation_author_id UUID            NOT NULL,
    updating_author_id UUID,
    -- info --
    config_category    config_category NOT NULL DEFAULT 'CC_BUSINESS_MODULE_SETTING',
    -- content --
    config_type        config_type     NOT NULL,
    kind               VARCHAR(50)     NOT NULL,
    content            JSONB           NOT NULL,
    -- keys --
    PRIMARY KEY (config_id)
);
