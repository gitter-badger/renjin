#  File src/library/base/R/serialize.R
#  Part of the R package, http://www.R-project.org
#
#  This program is free software; you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation; either version 2 of the License, or
#  (at your option) any later version.
#
#  This program is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#
#  A copy of the GNU General Public License is available at
#  http://www.r-project.org/Licenses/

saveRDS <-
    function(object, file = "", ascii = FALSE, version = NULL,
             compress = TRUE, refhook = NULL)
{
    if(is.character(file)) {
        if(file == "") stop("'file' must be non-empty string")
        mode <- if(ascii) "w" else "wb"
        con <- if (identical(compress, "bzip2")) bzfile(file, mode)
            else if (identical(compress, "xz")) xzfile(file, mode)
            else if(compress) gzfile(file, mode) else file(file, mode)
        on.exit(close(con))
    }
    else if(inherits(file, "connection")) {
        if (!missing(compress))
            warning("'compress' is ignored unless 'file' is a file name")
        con <- file
    }
    else
        stop("bad 'file' argument")
    invisible(.Internal(serializeToConn(object, con, ascii, version, refhook)))
}

readRDS <- function(file, refhook = NULL)
{
    if(is.character(file)) {
        con <- gzfile(file, "rb")
        on.exit(close(con))
    } else if(inherits(file, "connection"))
        con <- file
    else stop("bad 'file' argument")
    .Internal(unserializeFromConn(con, refhook))
}

serialize <-
    function(object, connection, ascii = FALSE, version = NULL, refhook = NULL)
{
    if (!is.null(connection)) {
        if (!inherits(connection, "connection"))
            stop("'connection' must be a connection")
        if (missing(ascii)) ascii <- summary(connection)$text == "text"
    }
    if (!ascii && inherits(connection, "sockconn"))
        .Call("R_serializeb", object, connection, version, refhook,
              PACKAGE="base")
    else
        .Call("R_serialize", object, connection, ascii, version, refhook,
              PACKAGE="base")
}

unserialize <- function(connection, refhook = NULL)
{
    if (typeof(connection) != "raw" &&
        !is.character(connection) &&
        !inherits(connection, "connection"))
        stop("'connection' must be a connection")
    .Call("R_unserialize", connection, refhook, PACKAGE="base")
}
