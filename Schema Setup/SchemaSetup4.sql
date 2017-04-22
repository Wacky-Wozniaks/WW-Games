CREATE TABLE public.passwordrecovery
(
    id serial NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    hash text COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL DEFAULT false,
    CONSTRAINT passwordrecovery_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;