import '@testing-library/cypress/add-commands'

Cypress.Commands.add('resetDatabase', () => cy.exec(`./reset-database.sh`))