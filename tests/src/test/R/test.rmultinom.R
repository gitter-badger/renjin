#
# Renjin : JVM-based interpreter for the R language for the statistical analysis
# Copyright © 2010-2019 BeDataDriven Groep B.V. and contributors
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, a copy is available at
# https://www.gnu.org/licenses/gpl-2.0.txt
#

# Generated by gen-dist-tests.R using GNU R version 3.3.1 (2016-06-21)
library(hamcrest)
library(stats)
set.seed(1)
test.rmultinom.1 <- function() assertThat({set.seed(1);rmultinom(n = 0x1p+0, size = c(0x1.4p+3, 0x1.4p+2, 0x1p+0, 0x0p+0), prob = c(0x0p+0, 0x1.999999999999ap-3, 0x1.3333333333333p-2, 0x1p-1))}, identicalTo(structure(c(0L, 1L, 3L, 6L), .Dim = c(4L, 1L))))
test.rmultinom.2 <- function() assertThat({set.seed(1);rmultinom(n = 1:5, size = c(0x1.4p+3, 0x1.4p+2, 0x1p+0, 0x0p+0), prob = c(0x0p+0, 0x1.999999999999ap-3, 0x1.3333333333333p-2, 0x1p-1))}, identicalTo(structure(c(0L, 1L, 3L, 6L), .Dim = c(4L, 1L))))
test.rmultinom.3 <- function() assertThat({set.seed(1);rmultinom(n = 0x1.ep+3, size = c(0x1.4p+3, 0x1.4p+2, 0x1p+0, 0x0p+0), prob = c(0x0p+0, 0x1.999999999999ap-3, 0x1.3333333333333p-2, 0x1p-1))}, identicalTo(structure(c(0L, 1L, 3L, 6L, 0L, 2L, 5L, 3L, 0L, 1L, 5L, 4L, 0L, 4L, 3L, 3L, 0L, 2L, 1L, 7L, 0L, 1L, 2L, 7L, 0L, 3L, 2L, 5L, 0L, 3L, 3L, 4L, 0L, 3L, 6L, 1L, 0L, 2L, 4L, 4L, 0L, 4L, 1L, 5L, 0L, 2L, 1L, 7L, 0L, 1L, 3L, 6L, 0L, 0L, 3L, 7L, 0L, 3L, 2L, 5L), .Dim = c(4L, 15L))))
test.rmultinom.4 <- function() assertThat({set.seed(1);rmultinom(n = numeric(0), size = c(0x1.4p+3, 0x1.4p+2, 0x1p+0, 0x0p+0), prob = c(0x0p+0, 0x1.999999999999ap-3, 0x1.3333333333333p-2, 0x1p-1))}, throwsError())
test.rmultinom.5 <- function() assertThat({set.seed(1);rmultinom(n = 0x1.8p+1, size = c(NA, 0x1.4p+2, 0x1p+0, 0x0p+0), prob = c(0x0p+0, 0x1.999999999999ap-3, 0x1.3333333333333p-2, 0x1p-1))}, throwsError())
