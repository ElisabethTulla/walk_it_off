
-- Table: achievement

-- DROP TABLE IF EXISTS achievement;

CREATE TABLE achievement
(
    id bigint NOT NULL DEFAULT nextval('achievement_id_seq'::regclass),
    name character varying(255) NOT NULL,
    required_steps integer,
    required_days_active integer,
    achievement_type character varying(255) NOT NULL,
    required_km numeric(10,2),
    CONSTRAINT achievement_pkey PRIMARY KEY (id)
);

-- Table: public.activity

-- DROP TABLE IF EXISTS activity;

CREATE TABLE activity
(
    id bigint NOT NULL DEFAULT nextval('activity_id_seq'::regclass),
    user_id bigint NOT NULL,
    activity_name character varying(255) NOT NULL,
    steps_logged integer,
    distance_logged_km numeric(10,2),
    logged_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT activity_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES user_walkitoff (id) MATCH SIMPLE
        ON DELETE CASCADE
);

-- Table: challenge

-- DROP TABLE IF EXISTS challenge;

CREATE TABLE challenge
(
    id bigint NOT NULL DEFAULT nextval('challenge_id_seq'::regclass),
    name character varying(255) NOT NULL,
    required_steps integer,
    required_achievement_id bigint,
    min_number_participants integer,
    max_number_participants integer,
    goal_steps integer,
    goal_distance_km numeric(10,2),
    started_at timestamp without time zone,
    goal_end timestamp without time zone,
    rewards_achievement_id bigint,
    required_km numeric(10,2),
    CONSTRAINT challenge_pkey PRIMARY KEY (id),
    CONSTRAINT fk_required_achievement FOREIGN KEY (required_achievement_id)
        REFERENCES achievement (id) MATCH SIMPLE
        ON DELETE CASCADE,
    CONSTRAINT fk_rewards_achievement FOREIGN KEY (rewards_achievement_id)
        REFERENCES achievement (id) MATCH SIMPLE
        ON DELETE CASCADE
);

-- Table: user_walkitoff

-- DROP TABLE IF EXISTS user_walkitoff;

CREATE TABLE user_walkitoff
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    active boolean DEFAULT true,
    gender character varying(50),
    birthday date,
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

-- Table: user_achievement

-- DROP TABLE IF EXISTS user_achievement;

CREATE TABLE user_achievement
(
    user_id bigint,
    achievement_id bigint,
    unlocked_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    unlocked boolean,
    CONSTRAINT fk_achievement FOREIGN KEY (achievement_id)
        REFERENCES achievement (id) MATCH SIMPLE
        ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES user_walkitoff (id) MATCH SIMPLE
        ON DELETE CASCADE
);

-- Table: public.user_challenge

-- DROP TABLE IF EXISTS user_challenge;

CREATE TABLE user_challenge
(
    user_id bigint,
    challenge_id bigint,
    entered_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    active boolean DEFAULT true,
    CONSTRAINT fk_challenge FOREIGN KEY (challenge_id)
        REFERENCES challenge (id) MATCH SIMPLE
        ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES user_walkitoff (id) MATCH SIMPLE
        ON DELETE CASCADE
);



