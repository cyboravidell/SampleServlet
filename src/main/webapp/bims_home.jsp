<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body> 
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">Book Management System</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <!-- Add your nav items here -->
            </ul>
            <form class="d-flex" role="search">
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
          </div>
        </div>
      </nav>
     
    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addBookModal">Add New Book</button>
    
    <table class="table table-info">
    <% out.print("<h2> This is the book Table </h2>") ;%>
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Book-Name</th>
          <th scope="col">Author</th>
          <th scope="col">Price</th>
          <th scope="col">Delete</th>
          <th scope="col">Update</th>
        </tr>
      </thead>
      <tbody>
      <%! int i = 1; %>
      <%
      
     List<Book> books = (List<Book>) request.getAttribute("books");
      for(Book book: books) {
	  
    	  %>
      
        <tr>
          <th scope="row"><%= i %></th>
          <td><%= book.getTitle() %></td>
          <td><%= book.getAuthor() %></td>
          <td><%= book.getPrice() %></td>
          
          <td><button type="button" class="btn btn-danger">Delete</button></td>
          <td><button type="button" class="btn btn-primary">Update</button></td>
        </tr>
        
        <% i = i+1;
      }
        %>
        
      </tbody>
    </table>

    <!-- Modal -->
    <div class="modal fade" id="addBookModal" tabindex="-1" aria-labelledby="addBookModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="addBookModalLabel">Add a New Book</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form id="addBookForm">
              <div class="mb-3">
                <label for="bookName" class="form-label">Book Name</label>
                <input type="text" class="form-control" id="bookName" required>
              </div>
              <div class="mb-3">
                <label for="author" class="form-label">Author</label>
                <input type="text" class="form-control" id="author" required>
              </div>
              <div class="mb-3">
                <label for="price" class="form-label">Price</label>
                <input type="number" class="form-control" id="price" required>
              </div>
              <button type="submit" class="btn btn-primary">Add Book</button>
            </form>
          </div>
        </div>
      </div>
    </div>

   
</body>
</html>