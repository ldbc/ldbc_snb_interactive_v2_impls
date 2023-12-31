SELECT TOP (10)
    Person.personId,
    firstName,
    lastName,
    Company.name,
    Person_workAt_Company.workFrom
FROM
    Person,
    Person_workAt_Company,
    Company,
    Country,
    (
        SELECT Person2Id
        FROM Person_knows_Person
        WHERE Person1Id = :personId
        UNION
        SELECT k2.Person2Id
        FROM Person_knows_Person k1, Person_knows_Person k2
        WHERE k1.Person1Id = :personId
          AND k1.Person2Id = k2.Person1Id
          AND k2.Person2Id <> :personId
    ) friend
WHERE Person.personId = friend.Person2Id
  AND Person.personId = Person_workAt_Company.PersonId
  AND Person_workAt_Company.CompanyId = Company.id
  AND Person_workAt_Company.workFROM < :workFromYear
  AND Country.id = Company.LocationPlaceId
  AND Country.name = :countryName
ORDER BY Person_workAt_Company.workFrom, Person.personId, Company.name DESC
;
