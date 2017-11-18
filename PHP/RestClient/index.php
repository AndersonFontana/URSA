<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>URSA REST Client</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap-reboot.min.css" rel="stylesheet">
    <link href="css/bootstrap-grid.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
  </head>

  <body>

    <div class="jumbotron">
      <div class="container">
        <h1 class="display-4">URSA REST Client</h1><br>
      </div>

      <div class="container">

        <div class="row">
          <div class="col-md-6">
            <div class="card">
              <div class="card-header">Incluir</div>
              <div class="card-block">

                <form action="incluir.php" method="post">
                  <div class="form-group">
                    <label for="incluir-codigo" class="col-form-label">Código</label>
                    <input class="form-control" type="number" value="0" id="incluir-codigo">
                  </div>
                  <div class="form-group">
                    <label for="incluir-codigo-cargo" class="col-form-label">Código do cargo</label>
                    <input class="form-control" type="number" value="0" id="incluir-codigo-cargo">
                  </div>
                  <div class="form-group">
                    <label for="incluir-descricao">Descrição</label>
                    <textarea class="form-control" id="incluir-descricao" rows="2"></textarea>
                  </div>
                  <div class="form-group">
                    <label for="incluir-acesso" class="col-form-label">Acesso</label>
                    <input class="form-control" type="number" value="0" id="incluir-acesso">
                  </div>
                  <div class="form-group">
                    <label for="incluir-ingresso" class="col-form-label">Ingresso</label>
                    <input class="form-control" type="datetime-local" value="2017-01-20T13:45:00" id="incluir-ingresso">
                  </div>
                  <div class="form-group">
                    <label for="incluir-fechada" class="col-form-label">Fechada</label>
                    <input class="form-control" type="datetime-local" value="2017-01-20T13:45:00" id="incluir-fechada">
                  </div>

                  <button type="submit" class="btn btn-success btn-block">Incluir</button>
                </form>

              </div>
            </div>
          </div>

          <div class="col-md-6">
            <div class="card">
              <div class="card-header">Alterar</div>
              <div class="card-block">

                <form action="alterar.php" method="post">
                  <div class="form-group">
                    <label for="alterar-codigo" class="col-form-label">Código</label>
                    <input class="form-control" type="number" value="0" id="alterar-codigo">
                  </div>
                  <div class="form-group">
                    <label for="alterar-codigo-cargo" class="col-form-label">Código do cargo</label>
                    <input class="form-control" type="number" value="0" id="alterar-codigo-cargo">
                  </div>
                  <div class="form-group">
                    <label for="alterar-descricao">Descrição</label>
                    <textarea class="form-control" id="alterar-descricao" rows="2"></textarea>
                  </div>
                  <div class="form-group">
                    <label for="alterar-acesso" class="col-form-label">Acesso</label>
                    <input class="form-control" type="number" value="0" id="alterar-acesso">
                  </div>
                  <div class="form-group">
                    <label for="alterar-ingresso" class="col-form-label">Ingresso</label>
                    <input class="form-control" type="datetime-local" value="2017-01-20T13:45:00" id="alterar-ingresso">
                  </div>
                  <div class="form-group">
                    <label for="alterar-fechada" class="col-form-label">Fechada</label>
                    <input class="form-control" type="datetime-local" value="2017-01-20T13:45:00" id="alterar-fechada">
                  </div>

                  <button type="submit" class="btn btn-primary btn-block">Alterar</button>
                </form>

              </div>
            </div>
          </div>
        </div>

        <hr>

        <div class="row">
          <div class="col-md-6">
            <div class="card">
              <div class="card-header">Excluir</div>
              <div class="card-block">

                <form action="excluir.php" method="post">
                  <div class="form-group">
                    <label for="excluir-codigo" class="col-form-label">Código</label>
                    <input class="form-control" type="number" value="0" id="excluir-codigo">
                  </div>

                  <button type="submit" class="btn btn-danger btn-block">Excluir</button>
                </form>

              </div>
            </div>
          </div>

          <div class="col-md-6">
            <div class="card">
              <div class="card-header">Consultar</div>
              <div class="card-block">

                <form action="consultar.php" method="post">
                  <div class="form-group">
                    <label for="consultar-codigo" class="col-form-label">Código</label>
                    <input class="form-control" type="number" value="0" id="consultar-codigo">
                  </div>

                  <button type="submit" class="btn btn-primary btn-block">Consultar</button>
                </form>

              </div>
            </div>
          </div>
        </div>

        <hr>

        <div class="row">
          <div class="col-md-6">
            <div class="card">
              <div class="card-header">Listar</div>
              <div class="card-block">

                <form action="listar.php" method="post">
                  <button type="submit" class="btn btn-primary btn-block">Listar</button>
                </form>

              </div>
            </div>
          </div>

          <div class="col-md-6">
            <div class="card">
              <div class="card-header">Listar Abertas</div>
              <div class="card-block">

                <form action="listarabertas.php" method="post">
                  <button type="submit" class="btn btn-primary btn-block">Listar Abertas</button>
                </form>

              </div>
            </div>
          </div>
        </div>

        <hr>

        <footer>
          <p>&copy; URSA REST Client</p>
        </footer>

      </div> <!-- /container -->
    </div> <!-- /jumbotron -->

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>