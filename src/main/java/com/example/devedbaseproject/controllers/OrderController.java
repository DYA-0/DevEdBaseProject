package com.example.devedbaseproject.controllers;

import com.example.devedbaseproject.models.*;
import com.example.devedbaseproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.devedbaseproject.tools.Tools.getLocalDateTime;

@Controller
public class OrderController {

    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private final IProductRepository productRepository;
    private final IEmployeeRepository employeeRepository;
    private final IOrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderController(IOrderRepository orderRepository, ICustomerRepository customerRepository, IProductRepository productRepository, IEmployeeRepository employeeRepository, IOrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @GetMapping("/history-order")
    public String findAll(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "FRONT/history-order";
    }

    @GetMapping("/order-update/{id}")
    public String updateOrderForm(@PathVariable("id") Long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("order", order);
        Customer customer = order.getCustomer();
//        Employee employee = order.getEmployee();
        List<OrderDetails> orderDetails = order.getOrderDetails();
        model.addAttribute("customer", customer);
//        model.addAttribute("employee", employee);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("products", productRepository.findAll());
        return "order/order-update";
    }

    @PostMapping("/order-update")
    public String updateOrder(@Valid Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order-update";
        }
        order.setActionDateTime(getLocalDateTime());
        order.setOrderCost(200);
        order.setOrderStatus("Не оплачен");
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/order-create")
    public String createOrderFrom(Order order, Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("orderDetails", orderDetailsRepository.findAll());
        return "order/order-create";
    }

    @PostMapping("/order-create")
    public String createOrder(@Valid Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order-create";
        }
        order.setActionDateTime(getLocalDateTime());
        order.setOrderCost(200);
        order.setOrderStatus("Не оплачен");

        orderRepository.save(order);

        //region Добавление тэгов продуктов из заказа в список тэгов клиента
        for (OrderDetails od: order.getOrderDetails()) {
            Optional<Product> tempproduct = productRepository.findById(od.getProduct().getId());
            Product product = new Product();

            if (tempproduct.isPresent()) {product = tempproduct.get();}
            else {System.out.println("Error Found, likely no product");}

            od.setProduct(product);
            orderDetailsRepository.save(od);

            if (product.getTags() == null) {
                System.out.println("У продукта нет тэгов");
            }
            else {
                for (Tag tag : product.getTags()) {
                    if (order.getCustomer().getTagCountMap().containsKey(tag)) {
                        order.getCustomer().getTagCountMap().put(tag, order.getCustomer().getTagCountMap().get(tag) + 1);
                    } else {
                        order.getCustomer().getTagCountMap().put(tag, 1);
                    }
                }
            }
            HashMap<Tag, Integer> sortedTags = order.getCustomer().getTagCountMap().entrySet().stream()
                    .sorted(Map.Entry.<Tag, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (a, b) -> { throw new AssertionError(); },
                            LinkedHashMap::new));
//                    .sorted(Comparator.comparingInt(e -> -e.getValue()))
            sortedTags.entrySet().forEach(System.out::println);
            order.getCustomer().setTagCountMap(sortedTags);
            customerRepository.save(order.getCustomer());
        }
        //endregion

        return "redirect:/history-order";
    }
    @GetMapping("/order-details/{id}")
    public String detailsView(@PathVariable("id") Long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid customer ID" + id));
        model.addAttribute("order", order);
        Customer customer = order.getCustomer();
//        Employee employee = order.getEmployee();
        List<OrderDetails> orderDetails = order.getOrderDetails();
        model.addAttribute("customer", customer);
//        model.addAttribute("employee", employee);
        model.addAttribute("orderDetails", orderDetails);

        return "order/order-details";
    }

}
