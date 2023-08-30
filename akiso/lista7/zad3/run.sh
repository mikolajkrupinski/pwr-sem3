#!/bin/bash

nasm -felf32 "$1".asm -g
ld -m elf_i386 "$1".o -o "$1" -g
rm "$1".o
