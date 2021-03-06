dataSource {
	dbCreate = "update"
    driverClassName = "org.apache.derby.jdbc.ClientDriver"
	url = "jdbc:derby://localhost:1527/inmobiliaria;create=true"
    username = ""
    password = ""
}

environments {
    development { //derby database
        dataSource {
			dbCreate = "update"
		    driverClassName = "org.apache.derby.jdbc.ClientDriver"
			url = "jdbc:derby://localhost:1527/inmobiliaria;create=true"
		    username = ""
		    password = ""
        }
    }
    test { //a in-memory database
        dataSource {
            jndiName = "java:/comp/env/jdbc/PostgreSQLDS"
        }
    }
    production { // PostgreSQL DB - equivalent to the one on OpenShift
        dataSource {
			dbCreate = "update"
		    driverClassName = "org.postgresql.Driver"
			url = "jdbc:postgresql://localhost:5432/app1"
		    username = "admin9u9qsdb"
		    password = "Q9d2HsMZVpKh"
        }
    }
}