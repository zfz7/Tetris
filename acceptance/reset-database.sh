#!/usr/bin/env sh

psql postgres://tetris_test:tetris_test@localhost:5433/tetris_test < ../backend/src/test/resources/setup.sql
