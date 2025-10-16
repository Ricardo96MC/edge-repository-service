CREATE TABLE edge (
  id         uuid PRIMARY KEY,
  source     varchar(255) NOT NULL,
  target     varchar(255) NOT NULL,
  url        varchar(1024) NOT NULL,
  created_at timestamp with time zone NOT NULL DEFAULT now()
);
