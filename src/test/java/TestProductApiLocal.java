import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import com.hil.basiccrud.controllers.ProductController;
import com.hil.basiccrud.entity.dto.ProductDto;
import com.hil.basiccrud.services.ProductService;
import java.util.HashSet;
import java.util.Set;
import org.mockito.Mockito;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestProductApiLocal {

    ProductController productController;
    ProductService productService;
    Set<ProductDto> expectedAll;

    @BeforeClass
    public void initService() {
        productService = Mockito.mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @BeforeTest
    public void beforeEachTest() {
        System.out.println("Executed before each test");
    }

    @AfterTest
    public void afterEachTest() {
        System.out.println("Executed after each test");
    }

    @BeforeGroups("mock_read_all_from_db")
    public void beforeGroupReadAll() {
        expectedAll = new HashSet<>();
        expectedAll.add(new ProductDto(1L, "Mock Product", "Very useful something", 29.99));
        when(productService.findAll()).thenReturn(expectedAll);
    }

    @Test(groups = "mock_read_all_from_db")
    public void testAllProductsEndpoint() {
        Set<ProductDto> allProducts = productController.findAll();

        assertNotNull(allProducts);
        assertNotEquals(allProducts.size(), 0);
        assertEquals(expectedAll, allProducts);
    }

    @Test
    public void testDeleteEndpoint() {
        Long mockProductId = 1L;

        try {
            Boolean deleted = productController.deleteProduct(mockProductId);
            if (Boolean.FALSE.equals(deleted)) {
                fail(String.format("Missing product id %s", mockProductId));
            }

            assertFalse(productController.findById(mockProductId).isPresent());

        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(String.format("Error performing delete on database for product id %s", mockProductId));
        }
    }

    @Test(expectedExceptions = Exception.class)
    public void testException() throws Exception {
    }
}
