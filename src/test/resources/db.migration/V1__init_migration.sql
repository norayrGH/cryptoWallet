CREATE TABLE "wallet_info"
(
    id                     BIGSERIAL NOT NULL
        CONSTRAINT wallet_info_pkey PRIMARY KEY,
    uuid                   UUID      NOT NULL,
    created_at             TIMESTAMP          DEFAULT now(),
    updated_at             TIMESTAMP          DEFAULT now(),
    wallet_owner_name      VARCHAR(50) NOT NULL,
    CONSTRAINT "UK_wallet_info_uuid" UNIQUE (uuid)
);
