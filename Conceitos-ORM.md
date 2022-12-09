# Propriedades das anotações de relacionamento

## Fetch
Por meio dessa propriedade podemos definir em que momento os dados da entidade ou lista de objetos ao qual outra entidade está relacionado serão carregados.
A propriedade `fetch` pode receber dois valores:
* `EAGER`: Carrega todas as informações da entidade/lista ao qual uma entidade está relacionada assim que um registro é buscado e mapeado como objeto.
* `LAZY`: Não carrega essas informações quando o registro é mapeado como objeto, os objetos dos relacionamentos que recebem essa propriedade vêm nulos inicialmente, eles só são carregados quando acessados com um método get.


## Optional
Propriedade que indica se um atributo pode ou não ser nulo, inicialmente ele possui o valor `true`, ou seja, o valor é opcional. Caso receba o valor `false`, então a atribuição de um valor é obrigatória.

Diferentemente do `nullable`, o optional é avaliado em tempo de execução, ou seja, se o campo estiver nulo e tiver `optional = false`, uma exceção será emitida **antes** da tentativa de persistência no banco de dados.

## Cascade
Essa propriedade indica quais operações realizadas numa entidade irão se perpetuar para o objeto da entidade a qual está relacionada.

Por exemplo, caso um relacionamento tenha a propriedade `cascade` com o valor `remove`, se a instância daquela entidade for removida, então o objeto ao qual está relacionada também será excluído.

No JPA, o cascade pode receber os seguintes valores:
* ALL: toda e qualquer operação na entidade será propagada para o objeto do relacionamento.
* PERSIST: quando o objeto for persistido, o objeto ao qual está relacionado também será.
* MERGE: se o objeto for atualizado na base de dados com um `merge`, o objeto ao qual está relacionado também será.
* REMOVE: se o objeto for removido da base de dados com um `remove`, o objeto ao qual está relacionado também será.
* REFRESH: se o objeto tiver seus dados sincronizados com a base de dados por meio de um `refresh`, o objeto ao qual está relacionado também será.
Se os dados de um deles tiverem sido alterados e caso as alterações não tenham sido *mergeadas* na *database*, os objetos terão seus dados restaurados e as alterações não salvas serão desfeitas.
* DETACH: se o objeto for removido do controle de um `entityManager` por meio de um `detach`, o objeto ao qual está relacionado também será.