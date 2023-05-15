delimiter //
create procedure insert_product(
    in sku varchar(16),
    in name varchar(32),
    in description varchar(64))
begin
    insert into product (sku, name, description)
    values (sku, name, description);
end //
