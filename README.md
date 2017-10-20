#Gerência de Oportunidades

A Universidade Regionalizada Salvador da América (URSA) é uma das maiores universidades de sua região. A região de abrangência da URSA compreende uma área essencialmente agrícola, com muitos produtores agropecuários de pequeno, grande e médio porte. Mesmo sendo agrícola, a região é muito bem servida de serviços de tecnologia da informação e comunicação, com grande conectividade e acesso indiscriminado por moradores e empresários a dispositivos computacionais de vários tipos. 

A URSA oferece diversos cursos de graduação aos moradores da região, dentre eles, os cursos de Agronomia, Engenharia Agronômica e Agronegócios (CST). Constantemente os produtores e as empresas da área agrícola entram em contato com os coordenadores da URSA para solicitar a indicação ou divulgar vagas de oportunidades: empregos, estágios ou bolsas (pesquisa, extensão ou desenvolvimento). As formas de chegada das oportunidades são tantas que os coordenadores e funcionários da URSA estão com problemas para gerenciar e divulgar essas oportunidades.

Os coordenadores dos cursos ligados ao agronegócio ficaram sabendo que você possui um grupo de amigos que cursa Ciência da Computação e solicitou que vocês montassem uma solução para esse problema (de forma gratuita, é claro pois os produtores rurais não tem como pagar). Seu trabalho consiste em criar um Sistema Distribuído que permita o envio de oportunidades por parte de produtores rurais ou empresas, o gerenciamento das oportunidades: quando chegaram, quais foram atendidas, que tipos são os mais procurados e uma forma para divulgar essas oportunidades. Como a região possui diversos ambientes computacionais, o sistema deve integrar várias tecnologias.

Deverão ser criadas aplicações com com interface de comunicação usando sockets UDP e TCP, Web Services SOAP e Web Services Rest. Deverá ser implementado um Banco de Dados para o armazenamento dos dados das oportunidades de emprego, estágio e bolsas disponibilizadas. A base de dados deve der a seguinte disposição:

##Oportunidade
#codigo: Integer (chave primária)
@codcargo: Integer (chave estrangeira cargo)
descricao: varchar(4000)
acesso: Integer (codificado)
ingresso: Timestamp
fechada: Timestamp

##Cargo
\#codcargo: Integer (chave primária)
descricao: varchar (100)
tipo: Integer

O campo 'acesso' serve para indicar para alunos de quais cursos a oportunidade é válida. Na URSA hoje são usados os seguintes acessos (alunos dos cursos):
1 – Somente Agronomia 5 – Agronomia ou Agronegocio
2 – Somente Engenharia Agronomica 6 – Eng. Agronomica ou Agronegocio
3 – Somente Agronogocio 7 – Todos os cursos
4 – Agronomia ou Eng. Agronomica


O campo 'tipo' serve para indicar o tipo da oportunidade para aquele cargo. Na URSA hoje são usados os seguintes tipos:
1 – Emprego formal 5 – Bolsa de pesquisa
2 – Estágio até 20h/semana 6 – Bolsa de extensão
3 – Estágio acima de 20h/semana 7 – Bolsa de graduação
4 – Estágio voluntário


O Banco de Dados pode ser implementado com qualquer tecnologia, uma vez que não é o foco do trabalho. A tabela cargo pode ser preenchida com registros por meio de uma ferramenta cliente de banco de dados. O SD não precisa alterar essa tabela, apenas consultá-la.
Deve-se implementar pelo menos os seguintes servidores/serviços (Java):
- Receptor de oportunidades que se comunica meio de um Socket UDP, porta 2001;
- Servidor de acesso ao BD que aceita conexões TCP, porta 1972;
- Serviço Web (Web Service) SOAP;
- Serviço Web (Web Service) Rest;

#O servidor/serviços deverão suportar as seguintes operações:
- Adiciona: Adiciona uma oportunidade ao BD. Caso código da oportunidade já exista, não adicionar e informar um erro;
- Altera: - Altera uma oportunidade ao BD. Caso código da oportunidade não exista, não alterar e informar um erro;
- Excluir: apaga uma oportunidade do BD. Caso o código da oportunidade não exista no BD, retornar essa informação;
- Consulta: retorna os dados de uma oportunidade. Caso o código da oportunidade não exista no BD, retornar essa informação;
- ListaOportunidades: deve ser recebido o código do cargo e retornar uma lista contendo as oportunidades para aquele cargo;
- ListaAbertas: deve ser recebido o tipo de oportunidade (ou nenhum tipo, indicando que são todos) e retornar as oportunidades em aberto para aquele tipo;

Os servidores/serviços ao receberem uma solicitação de inclusão de oportunidade devem automaticamente preencher o campo 'ingresso' com a data e hora de recebimento da solicitação no servidor/serviço. Nos pedidos de inclusão ou alteração de oportunidades, caso o cargo indicado não exista no BD, não realizar a operação e indicar essa informação.

O servidor que se comunica com socket UDP deve ser criado de forma que suporte apenas mensagens de até 100 caracteres e não deve suportar as operações de ListaOportunidades e ListaAbertas. Quando a oportunidade tiver mais de 100 caracteres, a oportunidade será enviada em mais de uma mensagem. Ele será usado apenas para interface dos usuários com equipamentos antigos.

O servidor que se comunica por TCP deve possuir uma interface com o usuário em modo texto, permitindo acesso aos funcionários da URSA para interagir com as oportunidades, a partir das operações previstas. Além disso, tanto ele quanto o servidor que se comunica com socket TCP deve construir um log de processamento das operações realizadas.

##Serviços Web:
Os serviços Web vão disponibilizar o acesso as mesmas operações que o servidor TCP. No caso de serviços Rest, devem ser implementadas operações em, pelo menos, quatro métodos HTTP: GET, PUT, POST e DELETE. A codificação dos dados será XML em SOAP e Json em Rest.
O trabalho deverá contar com os seguintes clientes:
- Cliente com interface de linha de comando para servidor UDP;
- Cliente com interface gráfica para interação com Serviço Web SOAP;
- Cliente Web para interação com o Serviço Web Rest.

O cliente para o servidor UDP deve receber seus argumentos em linha de comando, enviar a mensagem de solicitação ao servidor, aguardar a resposta e mostrar ao usuário. As opções disponíveis devem ser as mesmas que o servidor disponibiliza. O formato das informações é de livre escolha e o usuário deve ter uma forma de, se não lembra como passar as informações, ser informado em linha de comando, por exemplo com uma opção '-help' ou sem informar argumento algum. Lembrar que o servidor UDP só aceita mensagens de até 100 caracteres.

O cliente com interface gráfica para interação com o Web Service SOAP deve proporcionar acesso a todas as operações disponibilizadas pelo Serviço SOAP. O formato da interface gráfica é de livre escolha, desde que não sejam usadas tecnologias Web no cliente.

O cliente web para interação com o Web Service Rest deve proporcionar acesso a todas as
operações disponibilizadas pelo serviço com uma interface Web. O formato da interface Web bem
como a tecnologia usada para o desenvolvimento é de livre escolha.

A relação de funcionalidades descritas até aqui equivalem 70% da nota do trabalho. Os demais 30% serão computados se o grupo tiver funcionalidades extra (10% cada uma), como por exemplo:
- modelo de falhas (geral) e implementação do modelo no servidor UDP;
- tratamento de falhas para o serviço e consumidor SOAP ou Rest;
- um aplicativo móvel para os estudantes da URSA serem notificados de novas oportunidades;
- integração com alguma rede social: Facebook, Twitter, Instagram, WhatsApp para divulgação de novas oportunidades
- outros ... (consultar professor)

##Apresentação
O líder da equipe deverá realizar uma apresentação técnica: mostrar os modelos usados, as aplicações desenvolvidas e as tecnologias usadas (não usar código-fonte) do que foi construído (10 minutos). Em seguida, deve-se proceder a demonstração das implementações. Uma máquina ficará com o servidor/serviços, mostrando os logs de processamento enquanto outra ficará com os clientes/consumidores usados. Serão reservados dois projetores para a apresentação (40 min). A apresentação será no LCI e caso algum grupo necessite de acesso externo a algum serviço bloqueado, deve prever antecipadamente e solicitar ao professor para liberar o acesso. Em seguida, cada integrante será entrevistado individualmente para informar o que implementou e como foi realizada a implementação.

Serão realizadas duas avaliações: uma referente ao desenvolvimento do grupo e outra referente ao desenvolvimento individual de cada componente do grupo.
